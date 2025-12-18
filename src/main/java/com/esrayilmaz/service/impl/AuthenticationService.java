package com.esrayilmaz.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.AuthResponse;
import com.esrayilmaz.dto.DtoUser;
import com.esrayilmaz.dto.RefreshTokenRequest;
import com.esrayilmaz.exception.BaseException;
import com.esrayilmaz.exception.ErrorMessage;
import com.esrayilmaz.exception.MessageType;
import com.esrayilmaz.jwt.JWTService;
import com.esrayilmaz.model.RefreshToken;
import com.esrayilmaz.model.User;
import com.esrayilmaz.repository.RefreshTokenRepository;
import com.esrayilmaz.repository.UserRepository;
import com.esrayilmaz.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
	public DtoUser register(AuthRequest request) {
		DtoUser dtoUser = new DtoUser();
		User savedUser = userRepository.save(createUser(request));
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}
	
	private User createUser(AuthRequest request) {
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setCreatedTime(new Date());
		
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreatedTime(new Date());
		refreshToken.setExpiredDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		
		return refreshToken;
		
	}

	@Override
	public AuthResponse authenticate(AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
			authenticationProvider.authenticate(authenticationToken);
			
			Optional<User> optional = userRepository.findByUsername(request.getUsername());
			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optional.get()));
			
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
			
		} catch (Exception e) {
			new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
		return null;
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		
		if(optional.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
		}
		
		if(!isRefreshTokenValid(optional.get().getExpiredDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
		}
		
		User user = optional.get().getUser();
		String accessToken = jwtService.generateToken(user);
		RefreshToken refreshToken = createRefreshToken(user);
		RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}
	
	public boolean isRefreshTokenValid(Date expiredDate) {
		return new Date().before(expiredDate);
	}

	
}

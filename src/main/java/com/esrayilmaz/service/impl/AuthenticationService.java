package com.esrayilmaz.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.DtoUser;
import com.esrayilmaz.model.User;
import com.esrayilmaz.repository.UserRepository;
import com.esrayilmaz.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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

}

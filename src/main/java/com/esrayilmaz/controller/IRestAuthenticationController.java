package com.esrayilmaz.controller;

import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.AuthResponse;
import com.esrayilmaz.dto.DtoUser;
import com.esrayilmaz.dto.RefreshTokenRequest;
import com.esrayilmaz.model.RefreshToken;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest request); 
	
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}

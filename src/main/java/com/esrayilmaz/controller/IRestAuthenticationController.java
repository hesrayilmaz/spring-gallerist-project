package com.esrayilmaz.controller;

import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.AuthResponse;
import com.esrayilmaz.dto.DtoUser;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest request); 
	
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
}

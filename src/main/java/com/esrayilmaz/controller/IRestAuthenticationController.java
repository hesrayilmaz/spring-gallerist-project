package com.esrayilmaz.controller;

import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.DtoUser;

public interface IRestAuthenticationController {

	public RootEntity<DtoUser> register(AuthRequest request); 
}

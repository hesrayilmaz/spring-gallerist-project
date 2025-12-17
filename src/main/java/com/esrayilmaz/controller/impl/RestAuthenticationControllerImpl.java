package com.esrayilmaz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.esrayilmaz.controller.IRestAuthenticationController;
import com.esrayilmaz.controller.RestBaseController;
import com.esrayilmaz.controller.RootEntity;
import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.DtoUser;
import com.esrayilmaz.service.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

	@Autowired
	private IAuthenticationService authenticationService;
	
	@PostMapping("/register")
	@Override
	public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {
		return ok(authenticationService.register(request));
	}

}

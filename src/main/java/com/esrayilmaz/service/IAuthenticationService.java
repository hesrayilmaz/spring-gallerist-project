package com.esrayilmaz.service;

import com.esrayilmaz.dto.AuthRequest;
import com.esrayilmaz.dto.DtoUser;

public interface IAuthenticationService {

	public DtoUser register(AuthRequest request);
}

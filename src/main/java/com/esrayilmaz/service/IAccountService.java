package com.esrayilmaz.service;

import com.esrayilmaz.dto.account.DtoAccount;
import com.esrayilmaz.dto.account.DtoAccountIU;

public interface IAccountService {

	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}

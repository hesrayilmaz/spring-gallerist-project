package com.esrayilmaz.controller;

import com.esrayilmaz.dto.account.DtoAccount;
import com.esrayilmaz.dto.account.DtoAccountIU;

public interface IRestAccountController {

	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}

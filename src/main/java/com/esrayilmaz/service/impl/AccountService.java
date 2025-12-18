package com.esrayilmaz.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.account.DtoAccount;
import com.esrayilmaz.dto.account.DtoAccountIU;
import com.esrayilmaz.model.Account;
import com.esrayilmaz.repository.AccountRepository;
import com.esrayilmaz.service.IAccountService;

@Service
public class AccountService implements IAccountService{

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU) {
		DtoAccount dtoAccount = new DtoAccount();
		Account account = accountRepository.save(createAccount(dtoAccountIU));
		
		BeanUtils.copyProperties(account, dtoAccount);
		return dtoAccount;
	}
	
	private Account createAccount(DtoAccountIU dtoAccountIU) {
		Account account = new Account();
		account.setCreatedTime(new Date());
		
		BeanUtils.copyProperties(dtoAccountIU, account);
		return account;
	}
}

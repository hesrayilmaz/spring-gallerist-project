package com.esrayilmaz.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.account.DtoAccount;
import com.esrayilmaz.dto.address.DtoAddress;
import com.esrayilmaz.dto.customer.DtoCustomer;
import com.esrayilmaz.dto.customer.DtoCustomerIU;
import com.esrayilmaz.exception.BaseException;
import com.esrayilmaz.exception.ErrorMessage;
import com.esrayilmaz.exception.MessageType;
import com.esrayilmaz.model.Account;
import com.esrayilmaz.model.Address;
import com.esrayilmaz.model.Customer;
import com.esrayilmaz.repository.AccountRepository;
import com.esrayilmaz.repository.AddressRepository;
import com.esrayilmaz.repository.CustomerRepository;
import com.esrayilmaz.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAccount dtoAccount = new DtoAccount();
		DtoAddress dtoAddress = new DtoAddress();
		
		Customer customer = createCustomer(dtoCustomerIU);
		customerRepository.save(customer);
		
		BeanUtils.copyProperties(customer, dtoCustomer);
		BeanUtils.copyProperties(customer.getAccount(), dtoAccount);
		BeanUtils.copyProperties(customer.getAddress(), dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		dtoCustomer.setAddress(dtoAddress);
		
		return dtoCustomer;
	}
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if(optAccount.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
		}
		
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		}
		
		Customer customer = new Customer();
		
		customer.setCreatedTime(new Date());
		customer.setAccount(optAccount.get());
		customer.setAddress(optAddress.get());
		
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		
		return customer;
	}
}

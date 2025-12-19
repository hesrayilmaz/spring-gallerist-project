package com.esrayilmaz.dto.customer;

import java.util.Date;

import com.esrayilmaz.dto.DtoBase;
import com.esrayilmaz.dto.account.DtoAccount;
import com.esrayilmaz.dto.address.DtoAddress;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomer extends DtoBase {
	
	private String firstName;
	
	private String lastName;
	
	private String tckn;
	
	private Date dateOfBirth;
	
	private DtoAddress address;

	private DtoAccount account;
}

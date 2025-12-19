package com.esrayilmaz.dto.customer;

import java.util.Date;

import com.esrayilmaz.model.Account;
import com.esrayilmaz.model.Address;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCustomerIU {

	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String tckn;
	
	@NotNull
	private Date dateOfBirth;
	
	@NotNull
	private Long addressId;

	@NotNull
	private Long accountId;
}

package com.esrayilmaz.controller;

import com.esrayilmaz.dto.customer.DtoCustomer;
import com.esrayilmaz.dto.customer.DtoCustomerIU;

public interface IRestCustomerController {
	
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}

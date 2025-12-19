package com.esrayilmaz.service;

import com.esrayilmaz.dto.customer.DtoCustomer;
import com.esrayilmaz.dto.customer.DtoCustomerIU;

public interface ICustomerService {

	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}

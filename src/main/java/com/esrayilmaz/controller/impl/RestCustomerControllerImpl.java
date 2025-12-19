package com.esrayilmaz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esrayilmaz.controller.IRestCustomerController;
import com.esrayilmaz.controller.RestBaseController;
import com.esrayilmaz.controller.RootEntity;
import com.esrayilmaz.dto.customer.DtoCustomer;
import com.esrayilmaz.dto.customer.DtoCustomerIU;
import com.esrayilmaz.service.ICustomerService;

@RestController
@RequestMapping("/rest/api/customer")
public class RestCustomerControllerImpl extends RestBaseController implements IRestCustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@Override
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU) {
		return ok(customerService.saveCustomer(dtoCustomerIU));
	}
	
	
}

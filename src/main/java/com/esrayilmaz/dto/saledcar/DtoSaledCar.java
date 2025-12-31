package com.esrayilmaz.dto.saledcar;

import com.esrayilmaz.dto.DtoBase;
import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.customer.DtoCustomer;
import com.esrayilmaz.dto.gallerist.DtoGallerist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSaledCar extends DtoBase {

	private DtoCustomer customer;
	
	private DtoGallerist gallerist;
	
	private DtoCar car;
}

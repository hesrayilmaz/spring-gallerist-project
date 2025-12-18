package com.esrayilmaz.service;

import com.esrayilmaz.dto.address.DtoAddress;
import com.esrayilmaz.dto.address.DtoAddressIU;

public interface IAddressService {

	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}

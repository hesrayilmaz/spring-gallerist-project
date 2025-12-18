package com.esrayilmaz.controller;

import com.esrayilmaz.dto.address.DtoAddress;
import com.esrayilmaz.dto.address.DtoAddressIU;

public interface IRestAddressController {

	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}

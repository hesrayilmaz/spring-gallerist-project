package com.esrayilmaz.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.address.DtoAddress;
import com.esrayilmaz.dto.address.DtoAddressIU;
import com.esrayilmaz.model.Address;
import com.esrayilmaz.repository.AddressRepository;
import com.esrayilmaz.service.IAddressService;

@Service
public class AddressService implements IAddressService {

	@Autowired
    private AddressRepository addressRepository;

    AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

	@Override
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
		DtoAddress dtoAddress = new DtoAddress();
		Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
		
		BeanUtils.copyProperties(savedAddress, dtoAddress);
		return dtoAddress;
	}
	
	private Address createAddress(DtoAddressIU dtoAddressIU) {
		Address address = new Address();
		address.setCreatedTime(new Date());
		
		BeanUtils.copyProperties(dtoAddressIU, address);
		return address;
	}

}

package com.esrayilmaz.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.address.DtoAddress;
import com.esrayilmaz.dto.gallerist.DtoGallerist;
import com.esrayilmaz.dto.gallerist.DtoGalleristIU;
import com.esrayilmaz.exception.BaseException;
import com.esrayilmaz.exception.ErrorMessage;
import com.esrayilmaz.exception.MessageType;
import com.esrayilmaz.handler.GlobalExceptionHandler;
import com.esrayilmaz.model.Address;
import com.esrayilmaz.model.Gallerist;
import com.esrayilmaz.repository.AddressRepository;
import com.esrayilmaz.repository.GalleristRepository;
import com.esrayilmaz.service.IGalleristService;

@Service
public class GalleristService implements IGalleristService {

	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
		
		Gallerist gallerist = createGallerist(dtoGalleristIU);
		galleristRepository.save(gallerist);
		
		BeanUtils.copyProperties(gallerist, dtoGallerist);
		BeanUtils.copyProperties(gallerist.getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		
		return dtoGallerist;
	}
	
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if(optAddress.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		}
		
		Gallerist gallerist = new Gallerist();
		gallerist.setCreatedTime(new Date());
		gallerist.setAddress(optAddress.get());
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		
		return gallerist;
	}

}

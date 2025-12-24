package com.esrayilmaz.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.address.DtoAddress;
import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.gallerist.DtoGallerist;
import com.esrayilmaz.dto.galleristcar.DtoGalleristCar;
import com.esrayilmaz.dto.galleristcar.DtoGalleristCarIU;
import com.esrayilmaz.exception.BaseException;
import com.esrayilmaz.exception.ErrorMessage;
import com.esrayilmaz.exception.MessageType;
import com.esrayilmaz.model.Car;
import com.esrayilmaz.model.Gallerist;
import com.esrayilmaz.model.GalleristCar;
import com.esrayilmaz.repository.CarRepository;
import com.esrayilmaz.repository.GalleristCarRepository;
import com.esrayilmaz.repository.GalleristRepository;
import com.esrayilmaz.service.IGalleristCarService;

@Service
public class GalleristCarService implements IGalleristCarService {

	@Autowired
	private GalleristCarRepository galleristCarRepository;
	
	@Autowired
	private GalleristRepository galleristRepository;
	
	@Autowired
	private CarRepository carRepository;
	
	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		
		DtoAddress dtoAddress = new DtoAddress();
		
		GalleristCar galleristCar = createGalleristCar(dtoGalleristCarIU);
		
		BeanUtils.copyProperties(galleristCar, dtoGalleristCar);
		BeanUtils.copyProperties(galleristCar.getCar(), dtoCar);
		BeanUtils.copyProperties(galleristCar.getGallerist(), dtoGallerist);
		BeanUtils.copyProperties(galleristCar.getGallerist().getAddress(), dtoAddress);
		
		dtoGallerist.setAddress(dtoAddress);
		dtoGalleristCar.setCar(dtoCar);
		dtoGalleristCar.setGallerist(dtoGallerist);
		
		return dtoGalleristCar;
	}

	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if(optGallerist.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
		}
		
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if(optCar.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		}
		
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreatedTime(new Date());
		
		galleristCar.setGallerist(optGallerist.get());
		galleristCar.setCar(optCar.get());
		
		return galleristCar;
	}
}

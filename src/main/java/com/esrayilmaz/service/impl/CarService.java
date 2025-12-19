package com.esrayilmaz.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.car.DtoCarIU;
import com.esrayilmaz.model.Car;
import com.esrayilmaz.repository.CarRepository;
import com.esrayilmaz.service.ICarService;

@Service
public class CarService implements ICarService {

	@Autowired
	private CarRepository carRepository;
	
	@Override
	public DtoCar saveCar(DtoCarIU dtoCarIU) {
		DtoCar dtoCar = new DtoCar();
		Car car = createCar(dtoCarIU);
		
		BeanUtils.copyProperties(car, dtoCar);
		return dtoCar;
	}
	
	private Car createCar(DtoCarIU dtoCarIU) {
		Car car = new Car();
		car.setCreatedTime(new Date());
		
		BeanUtils.copyProperties(dtoCarIU, car);
		carRepository.save(car);
		
		return car;
	}

}

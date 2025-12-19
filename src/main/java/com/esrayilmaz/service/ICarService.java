package com.esrayilmaz.service;

import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.car.DtoCarIU;

public interface ICarService {

	public DtoCar saveCar(DtoCarIU dtoCarIU);
}

package com.esrayilmaz.controller;

import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.car.DtoCarIU;

public interface IRestCarController {

	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}

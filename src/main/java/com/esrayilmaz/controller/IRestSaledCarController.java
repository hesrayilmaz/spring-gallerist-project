package com.esrayilmaz.controller;

import com.esrayilmaz.dto.saledcar.DtoSaledCar;
import com.esrayilmaz.dto.saledcar.DtoSaledCarIU;

public interface IRestSaledCarController {

	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}

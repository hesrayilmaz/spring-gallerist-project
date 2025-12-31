package com.esrayilmaz.service;

import com.esrayilmaz.dto.saledcar.DtoSaledCar;
import com.esrayilmaz.dto.saledcar.DtoSaledCarIU;

public interface ISaledCarService {

	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}

package com.esrayilmaz.service;

import com.esrayilmaz.dto.galleristcar.DtoGalleristCar;
import com.esrayilmaz.dto.galleristcar.DtoGalleristCarIU;

public interface IGalleristCarService {

	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}

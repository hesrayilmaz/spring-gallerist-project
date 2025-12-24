package com.esrayilmaz.controller;

import com.esrayilmaz.dto.galleristcar.DtoGalleristCar;
import com.esrayilmaz.dto.galleristcar.DtoGalleristCarIU;

public interface IRestGalleristCarController {

	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}

package com.esrayilmaz.dto.galleristcar;

import com.esrayilmaz.dto.DtoBase;
import com.esrayilmaz.dto.car.DtoCar;
import com.esrayilmaz.dto.gallerist.DtoGallerist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoGalleristCar extends DtoBase {

	private DtoGallerist gallerist;
	
	private DtoCar car;
}

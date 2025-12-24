package com.esrayilmaz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esrayilmaz.controller.IRestGalleristCarController;
import com.esrayilmaz.controller.RestBaseController;
import com.esrayilmaz.controller.RootEntity;
import com.esrayilmaz.dto.galleristcar.DtoGalleristCar;
import com.esrayilmaz.dto.galleristcar.DtoGalleristCarIU;
import com.esrayilmaz.service.IGalleristCarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

	@Autowired
	IGalleristCarService galleristCarService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

}

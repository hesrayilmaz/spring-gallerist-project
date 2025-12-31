package com.esrayilmaz.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esrayilmaz.controller.IRestSaledCarController;
import com.esrayilmaz.controller.RestBaseController;
import com.esrayilmaz.controller.RootEntity;
import com.esrayilmaz.dto.saledcar.DtoSaledCar;
import com.esrayilmaz.dto.saledcar.DtoSaledCarIU;
import com.esrayilmaz.service.ISaledCarService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {

	@Autowired
	private ISaledCarService saledCarService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
		return ok(saledCarService.buyCar(dtoSaledCarIU));
	}

}

package com.esrayilmaz.controller;

import com.esrayilmaz.dto.gallerist.DtoGallerist;
import com.esrayilmaz.dto.gallerist.DtoGalleristIU;

public interface IRestGalleristController {

	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}

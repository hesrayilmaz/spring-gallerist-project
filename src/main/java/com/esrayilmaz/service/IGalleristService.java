package com.esrayilmaz.service;

import com.esrayilmaz.dto.gallerist.DtoGallerist;
import com.esrayilmaz.dto.gallerist.DtoGalleristIU;

public interface IGalleristService {

	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}

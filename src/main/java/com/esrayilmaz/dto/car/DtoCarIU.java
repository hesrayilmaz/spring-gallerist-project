package com.esrayilmaz.dto.car;

import java.math.BigDecimal;

import com.esrayilmaz.enums.CarStatusType;
import com.esrayilmaz.enums.CurrencyType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoCarIU {

	@NotEmpty
	private String licensePlate;
	
	@NotEmpty
	private String brand;
	
	@NotEmpty
	private String model;
	
	@NotNull
	private Integer productionYear;
	
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private CurrencyType currencyType;
	
	@NotNull
	private BigDecimal damagePrice;
	
	@NotNull
	private CarStatusType carStatusType;
}

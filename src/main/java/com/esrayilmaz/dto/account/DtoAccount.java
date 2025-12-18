package com.esrayilmaz.dto.account;

import java.math.BigDecimal;

import com.esrayilmaz.dto.DtoBase;
import com.esrayilmaz.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAccount extends DtoBase{
	
	private String accountNo;
	
	private String iban;
	
	private BigDecimal amount;
	
	private CurrencyType currencyType;
}

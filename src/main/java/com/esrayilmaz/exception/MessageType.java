package com.esrayilmaz.exception;

import lombok.Getter;

@Getter
public enum MessageType {

	NO_RECORD_EXIST("1004", "no record exist"),
	TOKEN_IS_EXPIRED("1005", "token is expired"),
	USERNAME_NOT_FOUND("1006", "username not found"),
	USERNAME_OR_PASSWORD_INVALID("1007", "username or password invalid"),
	REFRESH_TOKEN_NOT_FOUND("1008", "refresh token not found"),
	REFRESH_TOKEN_IS_EXPIRED("1009", "refresh token is expired"),
	CURRENCY_RATES_EXCEPTION("1010", "currency rates exception"),
	CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011", "customer amount is not enough"),
	CAR_IS_ALREADY_SALED("1012","car is already saled"),
	GENERAL_EXCEPTION("9999", "general exception");
	
	private String code;
	
	private String message;
	
	MessageType(String code, String message){
		this.code = code;
		this.message = message;
	}
}

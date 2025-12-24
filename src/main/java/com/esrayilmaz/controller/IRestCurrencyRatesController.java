package com.esrayilmaz.controller;

import com.esrayilmaz.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

	public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}

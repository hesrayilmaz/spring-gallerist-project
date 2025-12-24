package com.esrayilmaz.service;

import com.esrayilmaz.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}

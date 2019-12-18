package com.codeshare.springboot.microservice.example.exchangerate.model;

import java.math.BigDecimal;

public class ERConvertionRateResponseModel {
	
	private BigDecimal exchangeRateValue;
	private ERErrorModel error;
	
	public BigDecimal getRateValue() {
		return exchangeRateValue;
	}
	public void setRateValue(BigDecimal exchangeRateValue) {
		this.exchangeRateValue = exchangeRateValue;
	}
	public ERErrorModel getError() {
		return error;
	}
	public void setError(ERErrorModel error) {
		this.error = error;
	}

}

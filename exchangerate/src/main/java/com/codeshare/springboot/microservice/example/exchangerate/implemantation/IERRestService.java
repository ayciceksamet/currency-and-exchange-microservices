package com.codeshare.springboot.microservice.example.exchangerate.implemantation;

import com.codeshare.springboot.microservice.example.exchangerate.model.ERConvertionRateResponseModel;

public interface IERRestService {
	
	public ERConvertionRateResponseModel getExchangeRate(String object);
	
	public String ERgetTransactionID();

}

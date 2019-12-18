package com.codeshare.springboot.microservice.example.conversion.restservice;

import java.math.BigDecimal;

import com.codeshare.springboot.microservice.example.conversion.model.CCConvertionAmountResponseModel;

public interface ICCRestService {

	CCConvertionAmountResponseModel getCCAmount(BigDecimal amount, String from, String to);
	
}

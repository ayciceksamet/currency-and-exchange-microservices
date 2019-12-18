package com.codeshare.springboot.microservice.example.conversion.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CCConvertionAmountResponseModel {
	
	
	private BigDecimal amountConverted;
	private String transactionID;
	private CCErrorModel error;
	
	public BigDecimal getAmountValue() {
		return amountConverted;
	}
	public void setAmountValue(BigDecimal amountConverted) {
		this.amountConverted = amountConverted;
	}
	public CCErrorModel getError() {
		return error;
	}
	public void setError(CCErrorModel error) {
		this.error = error;
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String string) {
		this.transactionID = string;
	}


}

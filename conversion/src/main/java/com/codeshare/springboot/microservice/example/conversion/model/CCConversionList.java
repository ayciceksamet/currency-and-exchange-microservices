package com.codeshare.springboot.microservice.example.conversion.model;

import java.util.List;

public class CCConversionList {

	private List<CCDataObjectTransaction> listConversion;

	public List<CCDataObjectTransaction> getListConversion() {
		return listConversion;
	}

	public void setListConversion(List<CCDataObjectTransaction> listConversion) {
		this.listConversion = listConversion;
	}
}

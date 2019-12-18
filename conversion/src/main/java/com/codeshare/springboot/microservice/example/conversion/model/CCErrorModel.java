package com.codeshare.springboot.microservice.example.conversion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CCErrorModel {

	private String code;
	private String type;
	private String info;
	
	public CCErrorModel() {
		// TODO Auto-generated constructor stub
	}
	
	public CCErrorModel(String code, String type, String info) {
		super();
		this.code = code;
		this.type = type;
		this.info = info;
		// TODO Auto-generated constructor stub
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

}

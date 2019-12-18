package com.codeshare.springboot.microservice.example.conversion.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codeshare.springboot.microservice.example.conversion.model.CCConvertionAmountResponseModel;
import com.codeshare.springboot.microservice.example.conversion.model.CCDataObjectTransaction;
import com.codeshare.springboot.microservice.example.conversion.repository.CCRepository;
import com.codeshare.springboot.microservice.example.conversion.restservice.ICCRestService;


@RestController
public class CCController {
	
	@Autowired 
	ICCRestService execution;
	
    @Autowired
	CCRepository repository;
	
	@RequestMapping(value = "/conversion/ping", method = RequestMethod.GET)
	public String getPing(){
		
		return "Conversion Service id IDLE!";
	}
	
	@RequestMapping(value = "/conversion/{amount}/{from}/{to}", method = RequestMethod.POST)
	public ResponseEntity<CCConvertionAmountResponseModel> getRate(@PathVariable BigDecimal amount, @PathVariable String from, @PathVariable String to,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.OK;

		CCConvertionAmountResponseModel rm = execution.getCCAmount(amount, from, to);
		
		if(rm.getError()!=null && "400".equalsIgnoreCase(rm.getError().getCode())) {
			return new ResponseEntity<CCConvertionAmountResponseModel>(rm, HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<CCConvertionAmountResponseModel>(rm, status);
	}
	
	@RequestMapping(value = "/conversion/getbyid/{transactionid}", method = RequestMethod.GET)
	public ResponseEntity<List<CCDataObjectTransaction>> getTransaction(@PathVariable String transactionid,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.OK;

		List<CCDataObjectTransaction> responseModelList = new ArrayList<CCDataObjectTransaction>();
		
	    repository.findByAllTransactionID(transactionid).forEach(response -> responseModelList.add(response));
		
		return new ResponseEntity<List<CCDataObjectTransaction>>(responseModelList, status);
	}
	
	@RequestMapping(value = "/conversion/getbydate/{date}", method = RequestMethod.GET)
	public ResponseEntity<List<CCDataObjectTransaction>> getDate(@PathVariable String date,
			HttpServletRequest request) {

		HttpStatus status = HttpStatus.OK;

		List<CCDataObjectTransaction> responseModelList = new ArrayList<CCDataObjectTransaction>();
		
	    repository.findByAllTransactionID(date).forEach(response -> responseModelList.add(response));
		
		return new ResponseEntity<List<CCDataObjectTransaction>>(responseModelList, status);
	}
	
	
}
	


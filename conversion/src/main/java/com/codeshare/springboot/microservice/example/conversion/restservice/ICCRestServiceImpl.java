package com.codeshare.springboot.microservice.example.conversion.restservice;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.codeshare.springboot.microservice.example.conversion.model.CCConvertionAmountResponseModel;
import com.codeshare.springboot.microservice.example.conversion.model.CCDataObjectTransaction;
import com.codeshare.springboot.microservice.example.conversion.model.CCErrorModel;
import com.codeshare.springboot.microservice.example.conversion.model.CCModel;
import com.codeshare.springboot.microservice.example.conversion.repository.CCRepository;



	@Service
	public class ICCRestServiceImpl implements ICCRestService {
	

		@Autowired
		private Environment env;
		

	    @Autowired
	    CCRepository repository;
		
		@Override
		public CCConvertionAmountResponseModel getCCAmount(BigDecimal amount, String from, String to) {
			CCConvertionAmountResponseModel response = new CCConvertionAmountResponseModel();
			
			String pairValue = from.concat(to);
			
			RestTemplate restTemplate = new RestTemplate();
		    
		    Map<String, String> uriVariables = new HashMap<>();
		    uriVariables.put("currencypair", pairValue);
		    
		    HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			UriComponents builder = UriComponentsBuilder
					.fromHttpUrl(env.getProperty("exchange.service.uri"))
			        .buildAndExpand(uriVariables);

			HttpEntity<?> entity = new HttpEntity<>(headers);

			ResponseEntity<CCModel> responseEnt = restTemplate.exchange(
			        builder.toUriString(), 
			        HttpMethod.GET, 
			        entity, 
			        CCModel.class);
			
			CCModel amountResponse = responseEnt.getBody();
			
			HttpHeaders headersResponse = responseEnt.getHeaders();
			
			
			if(responseEnt!=null && !responseEnt.getStatusCode().is2xxSuccessful()) {
				CCErrorModel errormodel = new CCErrorModel();
		    	errormodel.setCode("404");
		    	errormodel.setType("Bad Request");
		    	errormodel.setInfo("No transaction process is done !");
		    	response.setError(errormodel);
		    	return response;
			}
		    
		    if("".equalsIgnoreCase(response.getTransactionID())) {
		    	CCErrorModel errormodel = new CCErrorModel();
		    	errormodel.setCode("400");
		    	errormodel.setType("Bad Request");
		    	errormodel.setInfo("No transaction process is done !");
		    	response.setError(errormodel);
		    	return response;
		    }
		    

			response.setAmountValue(amountResponse.getrateValue().multiply(amount));
		    
		    response.setError(amountResponse.getError());
		    
		    response.setTransactionID(headersResponse.getFirst("Transaction-ID"));
		    
		    CCDataObjectTransaction transaction = new CCDataObjectTransaction();
		    transaction.setAmountValue(response.getAmountValue());
		    transaction.setTransactionID(response.getTransactionID());
		    transaction.setDate(headersResponse.getDate());
		    transaction.setId(new Random().nextLong());
		    repository.insert(transaction);
		    
		    System.out.println(repository.findByTransactionID(transaction.getTransactionID()).getTransactionID());
		    
	
	        return response;
			
		}

}

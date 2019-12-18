package com.codeshare.springboot.microservice.example.conversion;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.codeshare.springboot.microservice.example.conversion.controller.CCController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CCControllerTest 
{
    @Autowired
    CCController ercontroller;
    
    @Autowired
    private MockMvc mockMvc;
    

    @Before
    public void setUp() throws Exception {
    }
    
    @Test
    public void testCCService_WrongURLPathAnd404NotFound() throws Exception 
    {
    	  mockMvc.perform(post("/conversion/"))
          .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testCCService_WithMissingCredentials() throws Exception 
    {
    	  mockMvc.perform(post("/conversion/10/USD"))
          .andExpect(status().is4xxClientError());
    }
    
    @Test
    public void testCCService_WithProperExchangeToValue() throws Exception 
    {
    	  mockMvc.perform(post("/conversion/100/USD/ALL"))
          .andExpect(status().isOk());
    }
    
    @Test
    public void testCCService_WithProperToValueAndNumberResult() throws Exception 
    {
    	  mockMvc.perform(post("/conversion/10000/USD/ALL"))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.amountValue").isNumber());
    }
     
    
}
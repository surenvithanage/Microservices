package com.web.stockservice.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {
	
	@Autowired
	public RestTemplate restTemplate;
	
	@GetMapping("/{username}")
	public List<StockQuote> getStock(@PathVariable("username") String username) {

//		Method 1
//		List<String> quotes = restTemplate.getForObject("http://localhost:8300/rest/db/" + username, List.class);
		
		
//		Method 2
		ResponseEntity<List<String>> quoteResponse = restTemplate.exchange("http://localhost:8300/rest/db/" + username, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() {});
		
		List<String> quotes = quoteResponse.getBody();
		quotes.stream().map(quote -> {
			try {
				return YahooFinance.get(quote);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		return null;
		
	}

}

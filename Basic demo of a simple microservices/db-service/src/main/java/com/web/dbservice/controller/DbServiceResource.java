package com.web.dbservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.dbservice.dto.Quotes;
import com.web.dbservice.model.Quote;
import com.web.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource {

	@Autowired
	private QuotesRepository quotesRepository;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		return quotesRepository.findByUserName(username);
	}
	
	@PostMapping("/add")
	public List<String> add(@RequestBody Quotes quotes) {
		// Method 1
		// quotes.getQuotes().stream().forEach(quote -> { quotesRepository.save(new Quote(quotes.getUsername(), quote));});
		// return getQuotes(quotes.getUsername());
		
		// Method 2
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUsername(), quote)).forEach(quote -> quotesRepository.save(quote));
		return null;
	}
}

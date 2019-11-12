package com.web.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.dbservice.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {

	List<String> findByUserName(String userName);

}

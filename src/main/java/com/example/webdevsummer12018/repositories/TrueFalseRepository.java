package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.TrueFalse;

public interface TrueFalseRepository
	extends CrudRepository<TrueFalse, Integer> {
	
}
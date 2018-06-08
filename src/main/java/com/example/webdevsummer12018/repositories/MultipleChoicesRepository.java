package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.MultipleChoice;
import com.example.webdevsummer12018.models.TrueFalse;

public interface MultipleChoicesRepository
	extends CrudRepository<MultipleChoice, Integer> {
	
}
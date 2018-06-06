package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.MultipleChoiceQuestion;
import com.example.webdevsummer12018.models.TrueFalseQuestion;

public interface MultipleChoicesQuestionRepository
	extends CrudRepository<MultipleChoiceQuestion, Integer> {
	
}
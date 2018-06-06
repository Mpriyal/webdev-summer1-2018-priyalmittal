package com.example.webdevsummer12018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Widget;

public interface ExamRepository
extends CrudRepository<Exam, Integer>{

}

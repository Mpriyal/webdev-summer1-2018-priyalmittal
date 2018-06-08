package com.example.webdevsummer12018.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.TrueFalse;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.TrueFalseRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueFalseService {

		@Autowired
		ExamRepository examRepository;
		@Autowired
		TrueFalseRepository trueFalseRepository;
		
		@PostMapping("/api/exam/{examId}/truefalse")
		public void createTrueFalseQuestion(@RequestBody
				TrueFalse question,
				@PathVariable("examId") int examId) {
			Optional<Exam> data = examRepository.findById(examId);
			if(data.isPresent()) {
				Exam exam = data.get();
				question.setExam(exam);
				trueFalseRepository.save(question);
			}
		}
		@PutMapping("/api/truefalse/{questionId}")
		public TrueFalse updateTrueFalse(@PathVariable("questionId") int questionId, 
											@RequestBody TrueFalse tfQuestion) {
			Optional<TrueFalse> data = trueFalseRepository.findById(questionId);
			if(data.isPresent()) {
				TrueFalse newTFQuestion = data.get();
				newTFQuestion.setTitle(tfQuestion.getTitle());
				newTFQuestion.setDescription(tfQuestion.getDescription());
				newTFQuestion.setPoints(tfQuestion.getPoints());
				newTFQuestion.setTrue(tfQuestion.isTrue());
				return trueFalseRepository.save(newTFQuestion);
			}
			return null;
		}
		
		@DeleteMapping("/api/truefalse/{questionId}")
		public void deleteTrueFalseQuestion(@PathVariable("questionId") int id) {
			trueFalseRepository.deleteById(id);
		}
		
	}

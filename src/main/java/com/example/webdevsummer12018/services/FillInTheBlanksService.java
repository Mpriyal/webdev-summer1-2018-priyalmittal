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
import com.example.webdevsummer12018.models.FillInTheBlank;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.FillInTheBlanksRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksService {

		@Autowired
		ExamRepository examRepository;
		@Autowired
		FillInTheBlanksRepository fillInTheBlanksRepository;
		
		@PostMapping("/api/exam/{examId}/blanks")
		public void createBlanksQuestion(@RequestBody FillInTheBlank fib,
								@PathVariable("examId") int id) {
			Optional<Exam> data = examRepository.findById(id);
			if(data.isPresent()) {
				Exam exam = data.get();
				fib.setExam(exam);
				fillInTheBlanksRepository.save(fib);
			}
		}
		@PutMapping("/api/blanks/{questionId}")
		public FillInTheBlank updateBlanksQuestion(@PathVariable("questionId") int questionId, 
											@RequestBody FillInTheBlank fibQuestion) {
			Optional<FillInTheBlank> data = fillInTheBlanksRepository.findById(questionId);
			if(data.isPresent()) {
				FillInTheBlank newFibQuestion = data.get();
				newFibQuestion.setTitle(fibQuestion.getTitle());
				newFibQuestion.setDescription(fibQuestion.getDescription());
				newFibQuestion.setPoints(fibQuestion.getPoints());
				return fillInTheBlanksRepository.save(newFibQuestion);
			}
			return null;
		}
		
		@DeleteMapping("/api/blanks/{questionId}")
		public void deleteQuestion(@PathVariable("questionId") int id) {
			fillInTheBlanksRepository.deleteById(id);
		}
}


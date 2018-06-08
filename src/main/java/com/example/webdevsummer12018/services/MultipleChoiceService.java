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
import com.example.webdevsummer12018.models.MultipleChoice;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.MultipleChoicesRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceService {

		@Autowired
		ExamRepository examRepository;
		@Autowired
		MultipleChoicesRepository multipleChoiceRepository;
		
		@PostMapping("/api/exam/{examId}/choice")
		public void createMultipleChoiceQuestion(@RequestBody
				MultipleChoice question,
				@PathVariable("examId") int examId) {
			Optional<Exam> data = examRepository.findById(examId);
			if(data.isPresent()) {
				Exam exam = data.get();
				question.setExam(exam);
				multipleChoiceRepository.save(question);
			}
		}
		@PutMapping("/api/choice/{questionId}")
		public MultipleChoice updateMultipleChoiceQuestion(@PathVariable("questionId") int questionId, 
											@RequestBody MultipleChoice mcQuestion) {
			Optional<MultipleChoice> data = multipleChoiceRepository.findById(questionId);
			if(data.isPresent()) {
				MultipleChoice newMCQuestion = data.get();
				newMCQuestion.setTitle(mcQuestion.getTitle());
				newMCQuestion.setDescription(mcQuestion.getDescription());
				newMCQuestion.setPoints(mcQuestion.getPoints());
				newMCQuestion.setOptions(mcQuestion.getOptions());
				return multipleChoiceRepository.save(newMCQuestion);
			}
			return null;
		}
		
		@DeleteMapping("/api/choice/{questionId}")
		public void deleteMultipleChoiceQuestion(@PathVariable("questionId") int id) {
			multipleChoiceRepository.deleteById(id);
		}
}

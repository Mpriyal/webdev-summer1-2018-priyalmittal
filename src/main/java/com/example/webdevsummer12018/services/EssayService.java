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

import com.example.webdevsummer12018.models.Essay;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.repositories.EssayRepository;
import com.example.webdevsummer12018.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class EssayService {

		@Autowired
		ExamRepository examRepository;
		@Autowired
		EssayRepository essayRepository;
		
		@PostMapping("/api/exam/{examId}/essay")
		public void createEssayQuestion(@RequestBody
				Essay question,
				@PathVariable("examId") int examId) {
			Optional<Exam> data = examRepository.findById(examId);
			if(data.isPresent()) {
				Exam exam = data.get();
				question.setExam(exam);
				essayRepository.save(question);
			}
		}
		@PutMapping("/api/essay/{questionId}")
		public Essay updateEssayQuestion(@PathVariable("questionId") int questionId, 
											@RequestBody Essay eQuestion) {
			Optional<Essay> data = essayRepository.findById(questionId);
			if(data.isPresent()) {
				Essay newEQuestion = data.get();
				newEQuestion.setTitle(eQuestion.getTitle());
				newEQuestion.setDescription(eQuestion.getDescription());
				newEQuestion.setPoints(eQuestion.getPoints());
				return essayRepository.save(newEQuestion);
			}
			return null;
		}
		
		@DeleteMapping("/api/essay/{questionId}")
		public void deleteEssayQuestion(@PathVariable("questionId") int id) {
			essayRepository.deleteById(id);
		}
}

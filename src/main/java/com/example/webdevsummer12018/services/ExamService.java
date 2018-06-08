package com.example.webdevsummer12018.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.MultipleChoice;
import com.example.webdevsummer12018.models.Question;
import com.example.webdevsummer12018.models.TrueFalse;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.EssayRepository;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.FillInTheBlanksRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.MultipleChoicesRepository;
import com.example.webdevsummer12018.repositories.TrueFalseRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	
	@Autowired
	ExamRepository examRepository;
	@Autowired
	LessonRepository lessonRepository;
	@Autowired
	EssayRepository essayRepository;
	@Autowired
	TrueFalseRepository trueFalseRepository;
	@Autowired
	MultipleChoicesRepository multipleChoiceRepository;
	@Autowired
	FillInTheBlanksRepository fbRepository;

	@GetMapping("/api/exam/{examId}/question")
	public List<Question> findAllQuestionsForExam(@PathVariable("examId") int examId) {
		Optional<Exam> optionalExam = examRepository.findById(examId);
		if(optionalExam.isPresent()) {
			Exam exam = optionalExam.get();
			List<Question> questions = exam.getQuestions();
			return questions;
		}
		return null;
	}
	
	@GetMapping("/api/truefalse/{questionId}")
	public TrueFalse findTrueFalseQuestionById(@PathVariable("questionId") int questionId) {
		System.out.println("I am Inside true false!!");
		Optional<TrueFalse> optional = trueFalseRepository.findById(questionId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam")
	public Iterable<Exam> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newlesson = lesson.get();
			List<Widget> widgetList = newlesson.getWidgets();
			List<Exam> examList = new ArrayList<Exam>(); 
			for (Widget widget: widgetList) {
				if (widget.getWidgetType() == "Exam")
				{
					examList.add((Exam) widget);
				}
			}
			return examList;
		}
		return null;	
	}
	
	@GetMapping("/api/multi/{questionId}")
	public MultipleChoice findMultipleQuestionById(@PathVariable("questionId") int questionId) {
		Optional<MultipleChoice> question = multipleChoiceRepository.findById(questionId);
		if(question.isPresent()) {
			return question.get();
		}
		return null;
	}

	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int examId) {
		examRepository.deleteById(examId);
	}
}

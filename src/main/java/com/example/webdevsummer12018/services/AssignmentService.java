package com.example.webdevsummer12018.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Assignment;
import com.example.webdevsummer12018.models.Course;
import com.example.webdevsummer12018.models.Exam;
import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.Question;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.AssignmentRepository;
import com.example.webdevsummer12018.repositories.ExamRepository;
import com.example.webdevsummer12018.repositories.LessonRepository;
import com.example.webdevsummer12018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
	
	@Autowired
	WidgetRepository repository;
	@Autowired
	ExamRepository examRepository;
	@Autowired
	AssignmentRepository assignmentRepository;
	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/assignment/{assignmentId}")
	public Optional<Assignment> findAllAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		return assignmentRepository.findById(assignmentId); 
	}

	@GetMapping("/api/assignment")
	public Iterable<Assignment> findAllAssignments() {
		return assignmentRepository.findAll(); 
	}
	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public Iterable<Assignment> findAllAssignmentForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			List<Widget> widgets = newLesson.getWidgets();
			List<Assignment> assignments = new ArrayList<Assignment>(); 
			for (Widget widget: widgets) {
				if (widget.getWidgetType() == "assignment")
				{
					assignments.add((Assignment) widget);
				}
			}
			return assignments;
		}
		return null;	
	}
	
	@PutMapping("/api/assignment/{assignmentId}")
	public Assignment updateAssignment(@PathVariable("assignmentId") int assignmentId, @RequestBody Assignment assignment) {
		Optional<Assignment> assign = assignmentRepository.findById(assignmentId);
		if(assign.isPresent()) {
			Assignment newAssignment = assign.get();
			newAssignment.setDescription(assignment.getDescription());
			newAssignment.setPoints(assignment.getPoints());
			newAssignment.setTitle(assignment.getTitle());
			return assignmentRepository.save(newAssignment);
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int assignmentId) {
		assignmentRepository.deleteById(assignmentId);
	}
}

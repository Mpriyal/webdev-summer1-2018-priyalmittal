package com.example.webdevsummer12018.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.Lesson;
import com.example.webdevsummer12018.models.Widget;
import com.example.webdevsummer12018.repositories.WidgetRepository;
import com.example.webdevsummer12018.repositories.LessonRespository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository widgetRepository;
	
	@Autowired
	LessonRespository lessonRepository;
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgets(@PathVariable("lessonId") int id) {
		Optional<Lesson> lesson = lessonRepository.findById(id);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			return newLesson.getWidgets();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveAllWidgets(@PathVariable("lessonId") int id, @RequestBody List<Widget> widgets) {
		List<Widget> widgetList1 = new ArrayList<>();
			Optional<Lesson> lesson = lessonRepository.findById(id);
			if(lesson.isPresent()) {
				Lesson newLesson = lesson.get();
				List<Widget> widgetList = newLesson.getWidgets();
				widgetRepository.deleteAll(widgetList);
				for (Widget widget2 : widgets) {
					widget2.setLesson(newLesson);
					widgetRepository.save(widget2);
				}
			}
//		for (Widget widget : widgetList1) {
//			widgetRepository.save(widget);
//		}
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int id) {
		Optional<Widget> widget = widgetRepository.findById(id);
		if(widget.isPresent()) {
			return widget.get();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(@RequestBody Widget widget,
			@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> lesson = lessonRepository.findById(lessonId);
		if(lesson.isPresent()) {
			Lesson newLesson = lesson.get();
			widget.setLesson(newLesson);
			return widgetRepository.save(widget);
		}
		return null;
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int id) {
		widgetRepository.deleteById(id);
	}
	
//	@GetMapping("/api/lesson/{lessonId}/widget")
//	public List<Widget> findWidgetsOfLesson(@PathVariable("lessonId") int id){
//		Optional<Lesson> lesson = lessonRepository.findById(id);
//		if(lesson.isPresent()) {
//			Lesson newLesson = lesson.get();
//			return newLesson.getWidgets();
//		}
//		return null;
//	}

}

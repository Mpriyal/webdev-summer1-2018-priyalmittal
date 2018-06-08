package com.example.webdevsummer12018.models;

import javax.persistence.Entity;

@Entity
public class FillInTheBlank extends Question {
	
	private String variables;
	private String description;
	private String questionText;
	private int points;
	
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}
	
}

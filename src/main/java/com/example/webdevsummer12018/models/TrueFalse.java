package com.example.webdevsummer12018.models;

import javax.persistence.Entity;

@Entity
public class TrueFalse extends Question {
	private boolean isTrue;
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isTrue() {
		return isTrue;
	}
	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
}

package com.example.webdevsummer12018.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String widgetType;
	private String text;
	private int headingWidgetSize;
	private int widgetOrder;
	private String href;
//	private String style;
//	private String width;
//	private String height;
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	enum ListType {
		ordered, unordered
	}
	
	private String src;
	private ListType listType;
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getWidgetType() {
		return widgetType;
	}
	
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	
	public int getWidgetOrder() {
		return widgetOrder;
	}
	
	public void setWidgetOrder(int widgetOrder) {
		this.widgetOrder = widgetOrder;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public Lesson getLesson() {
		return lesson;
	}
	
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	
	public int getHeadingWidgetSize() {
		return headingWidgetSize;
	}
	
	public void setHeadingWidgetSize(int headingWidgetSize) {
		this.headingWidgetSize = headingWidgetSize;
	}

//
//	public String getStyle() {
//		return style;
//	}
//
//	public void setStyle(String style) {
//		this.style = style;
//	}
//
//	public String getWidth() {
//		return width;
//	}
//
//	public void setWidth(String width) {
//		this.width = width;
//	}
//
//	public String getHeight() {
//		return height;
//	}
//
//	public void setHeight(String height) {
//		this.height = height;
//	}
//
//
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}


	public ListType getListType() {
		return listType;
	}

	public void setListType(ListType listType) {
		this.listType = listType;
	}
}

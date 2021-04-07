package com.temelius.ohjelmistoprojekti1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String questionLine;
	
	public Question() {}
	
	public Question(String questionLine) {
		super();
		this.questionLine = questionLine;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getQuestionLine() {
		return questionLine;
	}
	
	public void setQuestionLine(String questionLine) {
		this.questionLine = questionLine;
	}
	
	@Override
	public String toString() {
		return "Question [id="+ id + ", questionLine=" + questionLine + "]";
	}
}

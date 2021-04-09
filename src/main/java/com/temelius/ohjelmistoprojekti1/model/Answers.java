package com.temelius.ohjelmistoprojekti1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.temelius.ohjelmistoprojekti1.model.Question;

@Entity
public class Answers {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long answerid;
    private String answerline;
    private int answercount;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "questionid")
    private Question question;
    
    public Answers() {}
    
    public Answers(String answerline, int answercount, Question question) {
    	super();
    	this.answerline = answerline;
    	this.answercount = answercount;
    	this.question = question;
    }

	public Long getAnswerid() {
		return answerid;
	}

	public void setAnswerid(Long answerid) {
		this.answerid = answerid;
	}

	public String getAnswerline() {
		return answerline;
	}

	public void setAnswerline(String answerline) {
		this.answerline = answerline;
	}

	public int getAnswercount() {
		return answercount;
	}

	public void setAnswercount(int answercount) {
		this.answercount = answercount;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return answerline + " - " + answercount;
	}

	

	

}

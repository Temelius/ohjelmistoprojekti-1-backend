package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.temelius.ohjelmistoprojekti1.model.Question;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long answerid;
    private String answerline;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "questionid")
    private Question question;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="answer")
	private List<UserAnswer> userAnswer;
    
    public Answer() {}
    
    public Answer(String answerline, Question question) {
    	super();
    	this.answerline = answerline;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return answerline;
	}

	

	

}

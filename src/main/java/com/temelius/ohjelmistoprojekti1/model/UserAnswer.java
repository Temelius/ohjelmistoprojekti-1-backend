package com.temelius.ohjelmistoprojekti1.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.temelius.ohjelmistoprojekti1.model.Answer;

@Entity
public class UserAnswer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userAnswerId;
	private String userAnswerLine;
	
	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "answerid")
    private Answer answer;
	
	public UserAnswer() {}
	
	public UserAnswer(String userAnswerLine, Answer answer) {
		super();
		this.userAnswerLine = userAnswerLine;
		this.answer = answer;
	}

	public String getUserAnswerLine() {
		return userAnswerLine;
	}

	public void setUserAnswerLine(String userAnswerLine) {
		this.userAnswerLine = userAnswerLine;
	}

	public Long getUserAnswerId() {
		return userAnswerId;
	}

	public void setUserAnswerId(Long userAnswerId) {
		this.userAnswerId = userAnswerId;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return userAnswerLine;
	}
	
	
	
}

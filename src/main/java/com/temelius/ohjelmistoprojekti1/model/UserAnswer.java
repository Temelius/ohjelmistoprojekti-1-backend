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
	
	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "answerid")
    private Answer answer;
	
	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "questionid")
    private Question question;
	
	public UserAnswer() {}
	
	public UserAnswer(Answer answer) {
		super();
		this.answer = answer;
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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "UserAnswer [userAnswerId=" + userAnswerId + ", answer=" + answer + "]";
	}
	
	
	
}

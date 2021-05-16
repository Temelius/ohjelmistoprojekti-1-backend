package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long quizId;
	private String quizName;
	private boolean active;
	// Tarvitaanko esim. quiz author tms.
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="quiz")
	private List<Question> question;
	
	public Quiz() {}
	
	public Quiz(String quizName, boolean active) {
		super();
		this.quizName = quizName;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}
	
	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", quizName=" + quizName + ", question=" + question + "]";
	}
	
	
}

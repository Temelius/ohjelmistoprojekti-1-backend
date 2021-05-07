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
import com.temelius.ohjelmistoprojekti1.model.Answer;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionid;
	private String questionline;
	private String questionType;

	@ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quizId")
    private Quiz quiz;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Answer> answers;
	
	public Question() {}
	
	public Question(String questionline, String questionType, Quiz quiz) {
		super();
		this.questionline = questionline;
		this.questionType = questionType;
		this.quiz = quiz;
	}

	public Long getQuestionid() {
		return questionid;
	}

	public void setQuestionid(Long questionid) {
		this.questionid = questionid;
	}

	public String getQuestionline() {
		return questionline;
	}

	public void setQuestionline(String questionline) {
		this.questionline = questionline;
	}
	
	public String getQuestionType() {
		return questionType;
	}
	
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	@Override
	public String toString() {
		return questionline;
	}

	
	
	
}

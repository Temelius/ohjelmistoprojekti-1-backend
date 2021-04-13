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
	
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quizId")
    private Quiz quiz;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Answer> answers;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<UserAnswer> userAnswer;
	
	public Question() {}
	
	public Question(String questionline, Quiz quiz) {
		super();
		this.questionline = questionline;
		this.quiz = quiz;
	}

	public List<UserAnswer> getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(List<UserAnswer> userAnswer) {
		this.userAnswer = userAnswer;
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

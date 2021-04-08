package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.temelius.ohjelmistoprojekti1.model.Answers;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long questionid;
	private String questionline;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Answers> answers;
	
	public Question() {}
	
	public Question(String questionline) {
		super();
		this.questionline = questionline;
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

	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return questionline;
	}

	
	
	
}

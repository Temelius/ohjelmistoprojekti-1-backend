package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
	
	List<Question>findByQuestionLine(String questionLine);

}

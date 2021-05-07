package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserAnswerRepository extends CrudRepository<UserAnswer, Long> {
	//List<Answer> findByAnswerLine(String name);
}

package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
	List<Quiz> findByQuizName(String name);
}

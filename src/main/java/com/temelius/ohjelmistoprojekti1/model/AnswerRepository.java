package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answers, Long> {

    List<Answers> findByAnswerline(String answerline);
    
}
package com.temelius.ohjelmistoprojekti1.model;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {

    List<Answer> findByAnswerline(String answerline);
    


}
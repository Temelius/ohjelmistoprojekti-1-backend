package com.temelius.ohjelmistoprojekti1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.temelius.ohjelmistoprojekti1.model.Question;
import com.temelius.ohjelmistoprojekti1.model.QuestionRepository;

@SpringBootApplication
public class Ohjelmistoprojekti1Application {

	private static final Logger log = LoggerFactory.getLogger(Ohjelmistoprojekti1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Ohjelmistoprojekti1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner questionDemo(QuestionRepository qrepository) {
		return (args) -> {
			
			log.info("save a quesion");
			Question question1 = new Question("Ajan ja tehtävien hallinta");
			qrepository.save(question1);
			
			log.info("fetch all questions");
			for (Question question : qrepository.findAll( )) {
				log.info(question.toString());
			}
		};
	}

}

package com.temelius.ohjelmistoprojekti1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.temelius.ohjelmistoprojekti1.model.Question;
import com.temelius.ohjelmistoprojekti1.model.QuestionRepository;
import com.temelius.ohjelmistoprojekti1.model.Answers;
import com.temelius.ohjelmistoprojekti1.model.AnswerRepository;

@SpringBootApplication
public class Ohjelmistoprojekti1Application {

	private static final Logger log = LoggerFactory.getLogger(Ohjelmistoprojekti1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Ohjelmistoprojekti1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner questionDemo(QuestionRepository qrepository, AnswerRepository arepository) {
		return (args) -> {
			
			log.info("save a question");
			qrepository.save(new Question("Mitä kuuluu?"));
			qrepository.save(new Question("Mistä tuut?"));
			
			arepository.save(new Answers("Kukkuluuruu", qrepository.findByQuestionline("Mitä kuuluu?").get(0)));
			arepository.save(new Answers("kakkapuu", qrepository.findByQuestionline("Mitä kuuluu?").get(0)));
			arepository.save(new Answers("Keuruult", qrepository.findByQuestionline("Mistä tuut?").get(0)));

			log.info("fetch all questions and answers in db");
			for (Question question : qrepository.findAll( )) {
				log.info(question.toString());
				for (Answers answers : arepository.findAll( )) {
					log.info(answers.toString());
					
				}
			}
		};
	}

}

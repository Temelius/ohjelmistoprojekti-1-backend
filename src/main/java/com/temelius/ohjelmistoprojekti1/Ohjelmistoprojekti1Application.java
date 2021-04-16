package com.temelius.ohjelmistoprojekti1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.temelius.ohjelmistoprojekti1.model.User;
import com.temelius.ohjelmistoprojekti1.model.UserRepository;
import com.temelius.ohjelmistoprojekti1.model.Quiz;
import com.temelius.ohjelmistoprojekti1.model.QuizRepository;
import com.temelius.ohjelmistoprojekti1.model.Question;
import com.temelius.ohjelmistoprojekti1.model.QuestionRepository;
import com.temelius.ohjelmistoprojekti1.model.Answer;
import com.temelius.ohjelmistoprojekti1.model.AnswerRepository;
import com.temelius.ohjelmistoprojekti1.model.UserAnswer;
import com.temelius.ohjelmistoprojekti1.model.UserAnswerRepository;

@SpringBootApplication
public class Ohjelmistoprojekti1Application {

	private static final Logger log = LoggerFactory.getLogger(Ohjelmistoprojekti1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Ohjelmistoprojekti1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner questionDemo(
			QuizRepository quizRepository, 
			QuestionRepository qrepository, 
			AnswerRepository arepository, 
			UserAnswerRepository uarepository,
			UserRepository urepository) {
		return (args) -> {
			
			urepository.deleteAll();
			qrepository.deleteAll();
			quizRepository.deleteAll();
			uarepository.deleteAll();
			arepository.deleteAll();
			
			urepository.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@quizapp.fi"));
			urepository.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@quizapp.fi"));
			
			log.info("Save a quiz");
			quizRepository.save(new Quiz("Java kysely"));
			quizRepository.save(new Quiz("Kuulumiskysely"));
			
			log.info("save a question");
			qrepository.save(new Question("Mitä kuuluu?", quizRepository.findByQuizName("Kuulumiskysely").get(0)));
			qrepository.save(new Question("Mistä tuut?", quizRepository.findByQuizName("Kuulumiskysely").get(0)));
			qrepository.save(new Question("Onko java mielestäsi hauskaa?", quizRepository.findByQuizName("Java kysely").get(0)));
			qrepository.save(new Question("Jos vastasit edelliseen kyllä, niin oletko aivan varma?", quizRepository.findByQuizName("Java kysely").get(0)));
			qrepository.save(new Question("Jos vastasit edelliseen kyllä, niin vastauksesi on automaattisesti muutettu \"ei\"", quizRepository.findByQuizName("Java kysely").get(0)));
			
			log.info("Save an answer");
			arepository.save(new Answer("Hyvää", qrepository.findByQuestionline("Mitä kuuluu?").get(0)));
			arepository.save(new Answer("Huonoa", qrepository.findByQuestionline("Mitä kuuluu?").get(0)));
			arepository.save(new Answer("Keuruult", qrepository.findByQuestionline("Mistä tuut?").get(0)));
			arepository.save(new Answer("Turuust", qrepository.findByQuestionline("Mistä tuut?").get(0)));
			
			arepository.save(new Answer("Kyllä", qrepository.findByQuestionline("Onko java mielestäsi hauskaa?").get(0)));
			arepository.save(new Answer("Ei", qrepository.findByQuestionline("Onko java mielestäsi hauskaa?").get(0)));
			
			arepository.save(new Answer("Kyllä", qrepository.findByQuestionline("Jos vastasit edelliseen kyllä, niin oletko aivan varma?").get(0)));
			arepository.save(new Answer("Ei", qrepository.findByQuestionline("Jos vastasit edelliseen kyllä, niin oletko aivan varma?").get(0)));
			
			arepository.save(new Answer("Ok", qrepository.findByQuestionline("Jos vastasit edelliseen kyllä, niin vastauksesi on automaattisesti muutettu \"ei\"").get(0)));
			
			log.info("Save users answer");
			uarepository.save(new UserAnswer("Turuust", arepository.findByAnswerline("Turuust").get(0)));
			uarepository.save(new UserAnswer("Keuruult", arepository.findByAnswerline("Keuruult").get(0)));
			
			
			log.info("fetch all quizzes, questions and answers in db");
//			for (Quiz quiz : quizRepository.findAll()) {
//				log.info(quiz.toString());
//				for (Question question : qrepository.findAll( )) {
//					log.info(question.toString());
//					for (Answer answers : arepository.findAll( )) {
//						log.info(answers.toString());
//						
//					}
//				}
//			}
			
		};
	}

}

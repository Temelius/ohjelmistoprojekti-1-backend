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
			quizRepository.save(new Quiz("Ohjelmointikysely", true));
			quizRepository.save(new Quiz("Kuulumiskysely", true));
			quizRepository.save(new Quiz("Harrastuskysely", true));
			
			log.info("save a question");
			qrepository.save(new Question("Mitä kuuluu?", "radio", quizRepository.findByQuizName("Kuulumiskysely").get(0)));
			qrepository.save(new Question("Missä kaupungissa asut?", "text", quizRepository.findByQuizName("Kuulumiskysely").get(0)));
			qrepository.save(new Question("Onko sinulla nälkä?", "radio", quizRepository.findByQuizName("Kuulumiskysely").get(0)));

			qrepository.save(new Question("Mikä näistä ohjelmointikielistä on sinulle mieluisin?", "radio", quizRepository.findByQuizName("Ohjelmointikysely").get(0)));
			qrepository.save(new Question("Mihin haluaisit työllistyä tällä hetkellä?", "radio", quizRepository.findByQuizName("Ohjelmointikysely").get(0)));
			qrepository.save(new Question("Mikä ohjelmointikieli on tuntunut sinusta helpoimmalta?", "text", quizRepository.findByQuizName("Ohjelmointikysely").get(0)));
			qrepository.save(new Question("Montako vuotta sinulla on kokemusta ohjelmoinnista?", "text", quizRepository.findByQuizName("Ohjelmointikysely").get(0)));
			
			qrepository.save(new Question("Pelaatko videopelejä?", "radio", quizRepository.findByQuizName("Harrastuskysely").get(0)));
			qrepository.save(new Question("Harrastatko fyysistä liikuntaa?", "radio", quizRepository.findByQuizName("Harrastuskysely").get(0)));
			qrepository.save(new Question("Mikä on mieleisin harrastuksesi?", "text", quizRepository.findByQuizName("Harrastuskysely").get(0)));
			
			log.info("Save an answer");
			arepository.save(new Answer("Hyvää", qrepository.findByQuestionline("Mitä kuuluu?").get(0)));
			arepository.save(new Answer("Huonoa", qrepository.findByQuestionline("Mitä kuuluu?").get(0)));
			arepository.save(new Answer("Ei ole", qrepository.findByQuestionline("Onko sinulla nälkä?").get(0)));
			arepository.save(new Answer("Pikku hiljaa...", qrepository.findByQuestionline("Onko sinulla nälkä?").get(0)));
			arepository.save(new Answer("Näännyn kohta!", qrepository.findByQuestionline("Onko sinulla nälkä?").get(0)));
			
			arepository.save(new Answer("PHP", qrepository.findByQuestionline("Mikä näistä ohjelmointikielistä on sinulle mieluisin?").get(0)));
			arepository.save(new Answer("JavaScript", qrepository.findByQuestionline("Mikä näistä ohjelmointikielistä on sinulle mieluisin?").get(0)));
			arepository.save(new Answer("C", qrepository.findByQuestionline("Mikä näistä ohjelmointikielistä on sinulle mieluisin?").get(0)));
			arepository.save(new Answer("Java", qrepository.findByQuestionline("Mikä näistä ohjelmointikielistä on sinulle mieluisin?").get(0)));
			arepository.save(new Answer("Python", qrepository.findByQuestionline("Mikä näistä ohjelmointikielistä on sinulle mieluisin?").get(0)));
			
			arepository.save(new Answer("Front end", qrepository.findByQuestionline("Mihin haluaisit työllistyä tällä hetkellä?").get(0)));
			arepository.save(new Answer("Back end", qrepository.findByQuestionline("Mihin haluaisit työllistyä tällä hetkellä?").get(0)));
			arepository.save(new Answer("Full Stack", qrepository.findByQuestionline("Mihin haluaisit työllistyä tällä hetkellä?").get(0)));
			
			arepository.save(new Answer("Usein", qrepository.findByQuestionline("Pelaatko videopelejä?").get(0)));
			arepository.save(new Answer("Joskus", qrepository.findByQuestionline("Pelaatko videopelejä?").get(0)));
			arepository.save(new Answer("En pelaa", qrepository.findByQuestionline("Pelaatko videopelejä?").get(0)));
			
			arepository.save(new Answer("Säännöllisesti", qrepository.findByQuestionline("Harrastatko fyysistä liikuntaa?").get(0)));
			arepository.save(new Answer("Turhan harvoin", qrepository.findByQuestionline("Harrastatko fyysistä liikuntaa?").get(0)));
			arepository.save(new Answer("En ollenkaan", qrepository.findByQuestionline("Harrastatko fyysistä liikuntaa?").get(0)));
			
			log.info("Save users answer");
//			uarepository.save(new UserAnswer("Ei ole", arepository.findByAnswerline("Ei ole").get(0)));
//			uarepository.save(new UserAnswer("Pikku hiljaa...", arepository.findByAnswerline("Pikku hiljaa...").get(0)));
//			uarepository.save(new UserAnswer("Näännyn kohta!", arepository.findByAnswerline("Näännyn kohta!").get(0)));
			
			
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
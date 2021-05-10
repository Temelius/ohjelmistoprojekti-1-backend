package com.temelius.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.temelius.ohjelmistoprojekti1.model.Answer;
import com.temelius.ohjelmistoprojekti1.model.AnswerRepository;

import com.temelius.ohjelmistoprojekti1.model.Question;
import com.temelius.ohjelmistoprojekti1.model.QuestionRepository;

import com.temelius.ohjelmistoprojekti1.model.Quiz;
import com.temelius.ohjelmistoprojekti1.model.QuizRepository;

import com.temelius.ohjelmistoprojekti1.model.UserAnswer;
import com.temelius.ohjelmistoprojekti1.model.UserAnswerRepository;

@CrossOrigin
@RestController
@RequestMapping("api")
public class RestQuizController {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository qrepository;

	@Autowired
	private AnswerRepository arepository;

	@Autowired
	private UserAnswerRepository uarepository;
	
	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	/**
	 * REST API
	 */
	
	@GetMapping(value="/")
	public ResponseEntity<List<String>> getEndpoints() {
		return new ResponseEntity<>(
				requestMappingHandlerMapping
					.getHandlerMethods()
					.keySet()
					.stream()
					.map(RequestMappingInfo::toString)
					.collect(Collectors.toList()),
					HttpStatus.OK
				);
	}

	// RESTful service to get all quizzes
	@CrossOrigin
	@GetMapping(value = "/quiz/list", produces = "application/json")
	public List<Quiz> getAllQuizzes() {
		return (List<Quiz>) quizRepository.findAll();
	}
	
	// Get specific quiz, questions and answers
	@CrossOrigin
	@GetMapping(value = "/quiz/{id}", produces = "application/json")
	public Optional<Quiz> quizById(@PathVariable("id") Long id) {
		return quizRepository.findById(id);
	}
	
	// RESTful service to get all questions
	@CrossOrigin
	@GetMapping(value="/questions", produces = "application/json")
	public List<Question> getAllQuestions() {
		return (List<Question>) qrepository.findAll();
	}
	
	// TODO RESTful service to get all questions by quizId
	@CrossOrigin
	@GetMapping(value="/questions/{quizId}", produces="application/json")
	public List<Question> getAllQuestionsByQuiz(@PathVariable("quizId") Long quizId) {
		return (List<Question>) qrepository.findAllByQuizId(quizId);
	}

	// RESTful service question by question id
	@CrossOrigin
	@GetMapping(value = "/question/{id}", produces = "application/json")
	public Optional<Question> questionById(@PathVariable("id") Long id) {
		return qrepository.findById(id);
	}


	// See all answers
	@CrossOrigin
	@GetMapping(value = "/answers", produces = "application/json")
	public List<Answer> getAllAnswers() {
		return (List<Answer>) arepository.findAll();
	}

	// Get answer by id
	@CrossOrigin
	@GetMapping(value = "/answers/{id}", produces = "application/json")
	public Optional<Answer> answerById(@PathVariable("id") Long id) {
		return arepository.findById(id);
	}

	// See all useranswers
	@CrossOrigin
	@GetMapping(value = "/useranswers", produces = "application/json")
	public List<UserAnswer> getAllUserAnswers() {
		return (List<UserAnswer>) uarepository.findAll();
	}

	// Get useranswers by answer id
	@CrossOrigin
	@GetMapping(value = "/useranswers/{id}", produces = "application/json")
	public Optional<UserAnswer> userAnswerById(@PathVariable("id") Long id) {
		return uarepository.findById(id);
	}
	

	@CrossOrigin 
	@PostMapping(value="/useranswers")
	UserAnswer newUserAnswer(@RequestBody UserAnswer newUserAnswer) {
		return uarepository.save(newUserAnswer);
	}

}

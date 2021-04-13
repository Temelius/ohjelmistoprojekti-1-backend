package com.temelius.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temelius.ohjelmistoprojekti1.model.Answer;
import com.temelius.ohjelmistoprojekti1.model.AnswerRepository;

import com.temelius.ohjelmistoprojekti1.model.Question;
import com.temelius.ohjelmistoprojekti1.model.QuestionRepository;

import com.temelius.ohjelmistoprojekti1.model.Quiz;
import com.temelius.ohjelmistoprojekti1.model.QuizRepository;

import com.temelius.ohjelmistoprojekti1.model.UserAnswer;
import com.temelius.ohjelmistoprojekti1.model.UserAnswerRepository;

@Controller
public class QuizController {

	@Autowired
	private AnswerRepository arepository;

	@Autowired
	private QuestionRepository qrepository;

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private UserAnswerRepository uarepository;

	// Show all questions
	@RequestMapping(value = { "/", "/questionlist" })
	public String QuestionList(Model model) {
		model.addAttribute("quizzes", quizRepository.findAll());
		model.addAttribute("questions", qrepository.findAll());
		return "questionlist";
	}
	
	@RequestMapping(value = { "/add" })
	public String AddQuiz(Model model) {
		model.addAttribute("quiz", new Quiz());
		model.addAttribute("question", new Question());
		model.addAttribute("answer", new Answer());
		return "addquiz";
	}
	@RequestMapping(value = { "/addquestion/{id}" })
	public String AddQuestion(@PathVariable("id") Long quizId, Model model) {
		model.addAttribute("question", new Question());
		model.addAttribute("quiz", quizRepository.findById(quizId));
		return "addquestion";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Quiz quiz, Question question, Answer answer) {
		quizRepository.save(quiz);
		arepository.save(answer);
		qrepository.save(question);
		return "redirect:questionlist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteQuiz(@PathVariable("id") Long quizId, Model model) {
		quizRepository.deleteById(quizId);
		return "redirect:../questionlist";
	}
	/*
	 * REST api
	 */

	// RESTful service to get all questions
	@GetMapping(value = "/api/questions")
	public @ResponseBody List<Question> questionListRest() {
		return (List<Question>) qrepository.findAll();
	}

	// RESTful service question by question id
	@GetMapping(value = "/api/question/{id}")
	public @ResponseBody Optional<Question> findQuestionRest(@PathVariable("id") Long id) {
		return qrepository.findById(id);
	}

	// RESTful service to get all quizzes
	@GetMapping(value = "/api/quiz/list")
	public @ResponseBody List<Quiz> getAllQuizzes() {
		return (List<Quiz>) quizRepository.findAll();
	}

	// Get specific quiz, questions and answers
	@GetMapping(value = "/api/quiz/{id}")
	public @ResponseBody Optional<Quiz> quizRest(@PathVariable("id") Long id) {
		return quizRepository.findById(id);
	}

	@GetMapping(value = "/api/answers")
	public @ResponseBody List<Answer> getAllAnswers() {
		return (List<Answer>) arepository.findAll();
	}

	// See all answers
	@GetMapping(value = "/api/useranswers")
	public @ResponseBody List<UserAnswer> getAllUserAnswers() {
		return (List<UserAnswer>) uarepository.findAll();
	}

	// Get answer by id
	@GetMapping(value = "/api/answers/{id}")
	public @ResponseBody Optional<Answer> answersRest(@PathVariable("id") Long id) {
		return arepository.findById(id);
	}

}

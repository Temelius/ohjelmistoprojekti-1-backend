package com.temelius.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	/**
	 * Quiz related methods
	 */
	
	// Show all quizzes, questions and answers
	@RequestMapping(value = { "/", "/quizlist" })
	public String QuizList(Model model) {
		model.addAttribute("quizzes", quizRepository.findAll());
		model.addAttribute("questions", qrepository.findAll());
		return "quizlist";
	}
	
	// Add new quiz
	@RequestMapping(value = { "/add" })
	public String AddQuiz(Model model) {
		model.addAttribute("quiz", new Quiz());
		return "addquiz";
	}
	
	// Save newly added quiz
	@PostMapping(value="/savequiz")
	public String saveQuiz(Quiz quiz) {
		quizRepository.save(quiz);
		return "redirect:quizlist";
	}
	
	// Delete quiz by id
	@GetMapping(value = "/deletequiz/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteQuiz(@PathVariable("id") Long quizId, Model model) {
		quizRepository.deleteById(quizId);
		
		return "redirect:../quizlist";
	}
	
	
	/**
	 * Question related methods
	 */
	
	// Add new question, pass quizId to page
	@RequestMapping(value = { "/addquestion/{id}" })
	public String AddQuestion(@PathVariable("id") Long quizId, Model model) {
//		model.addAttribute("question", new Question());
		model.addAttribute("quiz", quizRepository.findById(quizId).get().getQuizId());
		return "addquestion";
	}
	
	// Save newly added question
	@PostMapping(value = "/savequestion")
	public String saveQuestion(
			@RequestParam(value="quizid", required=true) Long quizId,
			@RequestParam(value="questionline", required=true) String questionline) {
		
		Quiz quiz = quizRepository.findById(quizId).get();
		Question question = new Question(questionline, quiz);
		qrepository.save(question);
		
		return "redirect:quizlist";
	}
	
	// Delete question by id
	@GetMapping(value = "/deletequestion/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteQuestion(@PathVariable("id") Long questionid, Model model) {
		qrepository.deleteById(questionid);
		return "redirect:../quizlist";
	}
	
	
	/**
	 * Answer related methods
	 */	
	
	// Add new answer, pass questionId to page
	@RequestMapping(value="/addanswer/{id}")
	public String AddAnswer(@PathVariable("id") Long questionId, Model model) {
		model.addAttribute("question", qrepository.findById(questionId).get().getQuestionid());
		return "addanswer";
	}
	
	// Save newly added answer
	@PostMapping(value = "/saveanswer")
	public String saveAnswer(@RequestParam(value="questionid") Long questionid,
							 @RequestParam(value="answerline") String answerline) {
		Question question = qrepository.findById(questionid).get();
		arepository.save(new Answer(answerline, question));
		return "redirect:quizlist";
	}
    
    // Delete answer by id
    @GetMapping(value = "/deleteanswer/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteAnswer(@PathVariable("id") Long answerid, Model model) {
        arepository.deleteById(answerid);
        return "redirect:../quizlist";
    }
    

	/**
	 * REST api
	 */

	// RESTful service to get all questions
	@CrossOrigin
	@GetMapping(value = "/api/questions")
	public @ResponseBody List<Question> questionListRest() {
		return (List<Question>) qrepository.findAll();
	}

	// RESTful service question by question id
	@CrossOrigin
	@GetMapping(value = "/api/question/{id}")
	public @ResponseBody Optional<Question> findQuestionRest(@PathVariable("id") Long id) {
		return qrepository.findById(id);
	}

	// RESTful service to get all quizzes
	@CrossOrigin
	@GetMapping(value = "/api/quiz/list")
	public @ResponseBody List<Quiz> getAllQuizzes() {
		return (List<Quiz>) quizRepository.findAll();
	}

	// Get specific quiz, questions and answers
	@CrossOrigin
	@GetMapping(value = "/api/quiz/{id}")
	public @ResponseBody Optional<Quiz> quizRest(@PathVariable("id") Long id) {
		return quizRepository.findById(id);
	}

	// See all answers
	@CrossOrigin
	@GetMapping(value = "/api/answers")
	public @ResponseBody List<Answer> getAllAnswers() {
		return (List<Answer>) arepository.findAll();
	}

	// See all useranswers
	@CrossOrigin
	@GetMapping(value = "/api/useranswers")
	public @ResponseBody List<UserAnswer> getAllUserAnswers() {
		return (List<UserAnswer>) uarepository.findAll();
	}

	// Get useranswers by answer id
	@CrossOrigin
	@GetMapping(value = "/api/useranswers/{id}")
	public @ResponseBody Optional<UserAnswer> useranswersRest(@PathVariable("id") Long id) {
		return uarepository.findById(id);
	}

	// Get answer by id
	@CrossOrigin
	@GetMapping(value = "/api/answers/{id}")
	public @ResponseBody Optional<Answer> answersRest(@PathVariable("id") Long id) {
		return arepository.findById(id);
	}

}

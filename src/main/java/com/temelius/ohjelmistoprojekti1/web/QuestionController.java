package com.temelius.ohjelmistoprojekti1.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temelius.ohjelmistoprojekti1.model.Answers;
import com.temelius.ohjelmistoprojekti1.model.AnswerRepository;

import com.temelius.ohjelmistoprojekti1.model.Question;
import com.temelius.ohjelmistoprojekti1.model.QuestionRepository;


@Controller

public class QuestionController {
	
	@Autowired
	private AnswerRepository arepository;

	@Autowired
	private QuestionRepository qrepository;
	
	

	// Show all questions
	@RequestMapping(value = { "/", "/questionlist" })
	public String QuestionList(Model model) {
		model.addAttribute("questions", qrepository.findAll());
		return "questionlist";
	}
	
	
	//RIKKINÄISIÄ EI TOIMI
	 // Save given answer to answercount
//	@RequestMapping(value = "/saveanswer/{id}", method = RequestMethod.GET)
//	public String addAnswercount(@PathVariable("id") Long answerid, Model model) {
//		model.addAttribute("answers", arepository.findById(answerid));
//		return "saveanswer";
//
//	}
//	
//	@PostMapping(value = "/saveanswer/{id}")
//	public String save(Answers answers) {
//		answers.setAnswercount(1);
//		arepository.save(answers);
//		return "redirect:questionlist";
//	}
	
	
	
	// RESTful service to get all questions
	@GetMapping(value = "/questions") 
	public @ResponseBody List<Question> questionListRest() {
		return (List<Question>) qrepository.findAll();
	}

	// RESTful service question by question id
	@GetMapping(value = "/question/{id}")
	public @ResponseBody Optional<Question> findQuestionRest(@PathVariable("id") Long id) {
		return qrepository.findById(id);
	}
	
	
	
	
}

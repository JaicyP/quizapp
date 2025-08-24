package com.telusko.quizapp.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addQuestions(@RequestBody Question question) {
		return questionService.addQuestions(question);
	}
	
	@PostMapping("/addall")
	public ResponseEntity<String> addMoreQuestions(@RequestBody List<Question> questions) {
		return questionService.addMoreQuestions(questions);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("/all/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		   return questionService.getQuestionsByCategory(category);
	}
	

}

package com.telusko.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.entity.QuestionWrapper;
import com.telusko.quizapp.entity.Quiz;
import com.telusko.quizapp.entity.Response;
import com.telusko.quizapp.service.QuizService;

//hello
@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,
			@RequestParam int qNum,@RequestParam String qTitle) {
		return quizService.createQuiz(category,qNum,qTitle);
	}
	
	@GetMapping("/getqns/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable int id){
		return quizService.getQuestions(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
		return quizService.calculateResult(id,responses);
	}

}

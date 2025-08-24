package com.telusko.quizapp.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.repository.QuestionDao;

@Service
public class QuestionService {
    
	@Autowired
	QuestionDao  questionDao;
	
	public ResponseEntity<String> addQuestions(Question question) {
		try {
		   questionDao.save(question);
		   return new ResponseEntity<>("sucess",HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Try Again",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addMoreQuestions(List<Question> questions) {
		try {
		   questionDao.saveAll(questions);
		   return new ResponseEntity<>("Sucess",HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Try Again",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		  return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
		try {
		    return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

}

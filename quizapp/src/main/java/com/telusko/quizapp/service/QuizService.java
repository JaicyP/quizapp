package com.telusko.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.entity.QuestionWrapper;
import com.telusko.quizapp.entity.Quiz;
import com.telusko.quizapp.entity.Response;
import com.telusko.quizapp.repository.QuizDao;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;

	public ResponseEntity<String> createQuiz(String category, int qNum, String qTitle) {
	  try {
		Quiz quiz=new Quiz();
		quiz.setTitle(qTitle);
		List<Question> randomQuizQuestions=quizDao.getRandomQuestionsByCategory(category,qNum);
		quiz.setQuestions(randomQuizQuestions);
		quizDao.save(quiz);
		return new ResponseEntity<>("sucess",HttpStatus.CREATED);
	  }catch(Exception e) {
		 e.printStackTrace(); 
	  }
	  return new ResponseEntity<>("Try Again",HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(int id) {
	  try {
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> questionFromDb=quiz.get().getQuestions();
		List<QuestionWrapper> questionWrapperList=new ArrayList<>();
		for(Question q:questionFromDb) {
			questionWrapperList.add(new QuestionWrapper(q.getId(),q.getQuestionTitle(),
					q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4()));
		}
		return new ResponseEntity(questionWrapperList,HttpStatus.OK);
	  }catch(Exception e) {
		  e.printStackTrace();
	  }
		return new ResponseEntity(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
	 try {
		Optional<Quiz> quiz=quizDao.findById(id);
		List<Question> question=quiz.get().getQuestions();
		int i=0;
		int right=0;
		for(Response r:responses) {
			if(r.getResponse().equals(question.get(i).getRightAnswer())) {	
				right++;
			}
			i++;
		}
		return new ResponseEntity(right,HttpStatus.OK) ;
	 }catch(Exception e) {
		e.printStackTrace(); 
	 }
	 return new ResponseEntity(-1,HttpStatus.BAD_REQUEST);
	}
	
	

}

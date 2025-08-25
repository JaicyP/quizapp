package com.telusko.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.quizapp.entity.Question;
import com.telusko.quizapp.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
	@Query(value="SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :qNum",nativeQuery=true)
	List<Question> getRandomQuestionsByCategory(String category,int qNum);

}

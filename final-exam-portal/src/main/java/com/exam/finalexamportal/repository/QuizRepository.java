package com.exam.finalexamportal.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {

	public Quiz findByquizTitle(String title);

	public Set<Questions> findByquestions(String title);

}

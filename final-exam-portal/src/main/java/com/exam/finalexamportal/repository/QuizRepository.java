package com.exam.finalexamportal.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;

public interface QuizRepository extends MongoRepository<Quiz, String> {
//	@Query("")
//	public Quiz quiz findQuizfromQuestion(String questionId);
	public Quiz findByquizTitle(String title);

	public Set<Questions> findByquestions(String title);

	@Query(value = "quiz.findOne({ 'questions': { $elemMatch: { 'field': '_id', 'value': '?1' } } }, fn ...)")
	Quiz findQuizById(String id);

//	@Query("db.categories.findOne( { _id: /"?1"/ } ).parent")
//	@Query("db.categories.findOne( { _id: "MongoDB" } ).parent")

}

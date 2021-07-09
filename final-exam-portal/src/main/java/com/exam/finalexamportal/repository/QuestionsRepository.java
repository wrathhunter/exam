package com.exam.finalexamportal.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;

public interface QuestionsRepository extends MongoRepository<Questions, String> {


}

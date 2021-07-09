package com.exam.finalexamportal.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Quiz;

public interface ExamCategoryRepository extends MongoRepository<ExaminationCategory, String>{



}

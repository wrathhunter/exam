package com.exam.finalexamportal.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;

public interface QuestionService {
	public Questions createQuestion(Questions question,String quizName,String examCategoryName,String examName);
	public Questions updateQuestion(Questions question, String quizName,String examCategoryName,String examName);
	public void deleteQuestion(Questions question, String quizName,String examCategoryName,String examName);
	public Set<Questions> getQuestions(String quizName,String examCategoryName,String examName);
	public Questions getOneQuestion(String qId);
}

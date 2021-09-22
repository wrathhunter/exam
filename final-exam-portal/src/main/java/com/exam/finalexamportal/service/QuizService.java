package com.exam.finalexamportal.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;

public interface QuizService {
	public Quiz addQuiz(Quiz quiz,String examCategoryName,String examName);
	public Quiz updateQuiz(Quiz quiz, String examCategoryName, String examName) throws Exception;
	public void deleteQuiz(String quizName, String examCategoryName, String examName) throws Exception;
	public Set<Quiz> getQuizs(String examCategoryName, String examName);
	public Quiz getQuiz(String quizName, String examCategoryName, String examName);
	public Set<Questions> getQuestionsOfQuiz(String quizName, String examCategoryName, String examName);
	public Set<Questions> getQuestionOfQuiz(String quizId);
	public Quiz getExactQuiz(String quizId);
	public List<Quiz> getCreatersQuizzes(String createrId);
//	public Quiz getQuizFromQuestions(String questionId);
}

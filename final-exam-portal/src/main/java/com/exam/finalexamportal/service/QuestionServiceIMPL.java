package com.exam.finalexamportal.service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;
import com.exam.finalexamportal.repository.ExamCategoryRepository;
import com.exam.finalexamportal.repository.ExaminationRepository;
import com.exam.finalexamportal.repository.QuestionsRepository;
import com.exam.finalexamportal.repository.QuizRepository;
import com.exam.finalexamportal.repository.UserRepository;
@Service(value = "questionService")
@Transactional
public class QuestionServiceIMPL implements QuestionService{
	@Autowired
	private UsersDetailsServiceIMPL userDetailsServiceImpl;
	@Autowired
	private QuestionsRepository questionsRepository;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private ExamCategoryRepository examCategoryRepository;
	@Autowired
	private ExaminationRepository examinationRepository;
	@Autowired
	private UserRepository userRepository;
	@Override
	public Questions createQuestion(Questions question, String quizName, String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations=user.getExaminations();
		for (Examination examination : examinations) {
			if(examination.getName().equals(examName))
			{
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if(examinationCategory.getCategoryTitle().equals(examCategoryName))
					{
						for (Quiz quizs : examinationCategory.getQuizzes()) {
							if(quizs.getQuizTitle().equals(quizName))
							{
								Questions newQuestion=new Questions();
								newQuestion.setAnswer(question.getAnswer());
								newQuestion.setContent(question.getContent());
								newQuestion.setImage(question.getImage());
								newQuestion.setOption1(question.getOption1());
								newQuestion.setOption2(question.getOption2());
								newQuestion.setOption3(question.getOption3());
								newQuestion.setOption4(question.getOption4());
								newQuestion.setCreaterId(user.getId());
								newQuestion.setQuizIdOfTheQuestion(quizs.getQuizId());
								newQuestion.setExamIdOfTheQuestion(examination.getId());
								newQuestion.setCategoryIdOfTheQuestion(examinationCategory.getCategoryId());
								quizs.getQuestions().add(newQuestion);
								quizs.setQuestions(quizs.getQuestions());
								examination.getExaminationCategories().add(examinationCategory);
								examination.setExaminationCategories(examination.getExaminationCategories());
//								user.getExaminations().add(examination);
								user.setExaminations(user.getExaminations());
								Questions newCreatedQuestions=questionsRepository.save(newQuestion);
								quizRepository.save(quizs);
								examCategoryRepository.save(examinationCategory);
								examinationRepository.save(examination);
								userRepository.save(user);
								return newCreatedQuestions;
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Questions updateQuestion(Questions question, String quizName, String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations=user.getExaminations();
		for (Examination examination : examinations) {
			if(examination.getName().equals(examName))
			{
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if(examinationCategory.getCategoryTitle().equals(examCategoryName))
					{
						for (Quiz quizs : examinationCategory.getQuizzes()) {
							if(quizs.getQuizTitle().equals(quizName))
							{
								for (Questions newQuestions : quizs.getQuestions()) {
									if(newQuestions.getQuestionId().equals(question.getQuestionId()))
									{
										newQuestions.setAnswer(question.getAnswer());
										newQuestions.setContent(question.getContent());
										newQuestions.setImage(question.getImage());
										newQuestions.setOption1(question.getOption1());
										newQuestions.setOption2(question.getOption2());
										newQuestions.setOption3(question.getOption3());
										newQuestions.setOption4(question.getOption4());
										quizs.setQuestions(quizs.getQuestions());
										examination.getExaminationCategories().add(examinationCategory);
										examination.setExaminationCategories(examination.getExaminationCategories());
										user.getExaminations().add(examination);
										user.setExaminations(user.getExaminations());
										Questions newCreatedQuestions=questionsRepository.save(newQuestions);
										quizRepository.save(quizs);
										examCategoryRepository.save(examinationCategory);
										examinationRepository.save(examination);
										userRepository.save(user);
										return newCreatedQuestions;
									}
								}
								
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public void deleteQuestion(String questionId, String quizName, String examCategoryName, String examName) throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		int count1=0;
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations=user.getExaminations();
		
		
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				count++;
			}
		}
		if (count == 0) {
			throw new Exception("exam not found");
		} else {
		
		
		for (Examination examination : examinations) {
			if(examination.getName().equals(examName))
			{
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if(examinationCategory.getCategoryTitle().equals(examCategoryName))
					{
						for (Quiz quizs : examinationCategory.getQuizzes()) {
							if(quizs.getQuizTitle().equals(quizName))
							{
								for (Questions newQuestions : quizs.getQuestions()) {
//									if(newQuestions.getQuestionId().equals(questionId))
//									{
//										count1++;
//									}
//									else {
//										{
//											continue;
//										}
//									}
//									if (count1 == 0) {
//										throw new Exception("question not found");
//									} else {
									if(newQuestions.getQuestionId().equals(questionId))
									{
										quizs.getQuestions().remove(newQuestions);
										quizs.setQuestions(quizs.getQuestions());
										examination.getExaminationCategories().add(examinationCategory);
										examination.setExaminationCategories(examination.getExaminationCategories());
										user.getExaminations().add(examination);
										user.setExaminations(user.getExaminations());
										questionsRepository.delete(newQuestions);
										quizRepository.save(quizs);
										examCategoryRepository.save(examinationCategory);
										examinationRepository.save(examination);
										userRepository.save(user);
										break;
									}
									else {
										{
											continue;
										}
									}
								}
								
							}
						}
					}
				}
			}
		}
		}
	}

	@Override
	public Set<Questions> getQuestions(String quizName, String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations=user.getExaminations();
		for (Examination examination : examinations) {
			if(examination.getName().equals(examName))
			{
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if(examinationCategory.getCategoryTitle().equals(examCategoryName))
					{
						for (Quiz quizs : examinationCategory.getQuizzes()) {
							if(quizs.getQuizTitle().equals(quizName))
							{
								return quizs.getQuestions();
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Questions getOneQuestion(String qId) {
		// TODO Auto-generated method stub
 		Optional<Questions> question=questionsRepository.findById(qId);
		Questions newQuestion=question.orElseThrow();
		return newQuestion;
	}
	

	
	




	
	
}

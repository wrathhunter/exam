package com.exam.finalexamportal.service;

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

@Service(value = "quizService")
@Transactional
public class QuizServiceIMPL implements QuizService {
	@Autowired
	private UsersDetailsServiceIMPL userDetailsServiceImpl;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private ExamCategoryRepository examCategoryRepository;
	@Autowired
	private ExaminationRepository examinationRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuestionsRepository questionsRepository;

	@Override
	public Quiz addQuiz(Quiz quiz, String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if (examinationCategory.getCategoryTitle().equals(examCategoryName)) {
						Quiz newQuiz = new Quiz(quiz.getActive(), quiz.getQuizTitle(), quiz.getQuizDescription(),
								quiz.getQuizMaxMarks(), quiz.getQuizNoOfQuestions(), quiz.getExaminationType());
						examinationCategory.getQuizzes().add(newQuiz);
						examinationCategory.setQuizzes(examinationCategory.getQuizzes());
						examination.getExaminationCategories().add(examinationCategory);
						examination.setExaminationCategories(examination.getExaminationCategories());
						user.getExaminations().add(examination);
						user.setExaminations(user.getExaminations());
						Quiz newCreatedQuiz = quizRepository.save(newQuiz);
						examCategoryRepository.save(examinationCategory);
						examinationRepository.save(examination);
						userRepository.save(user);
						return newCreatedQuiz;
					}
				}
			}
		}
		return null;
	}

	@Override
	public Quiz updateQuiz(Quiz quiz, String examCategoryName, String examName) throws Exception {
		// TODO Auto-generated method stub
//		int count=0;
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();

//		for (Examination examination : examinations) {
//			if (examination.getName().equals(examName)) {
//				count++;
//			}
//		}
//		if(count==0)
//		{
//			throw new Exception("exam not found");
//		}
//		else {

		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if (examinationCategory.getCategoryTitle().equals(examCategoryName)) {
						for (Quiz newQuiz : examinationCategory.getQuizzes()) {
							if (newQuiz.getQuizTitle().equals(quiz.getQuizTitle())) {
								newQuiz.setActive(quiz.getActive());
								newQuiz.setExaminationType(quiz.getExaminationType());
								newQuiz.setQuestions(quiz.getQuestions());
								newQuiz.setQuizDescription(quiz.getQuizDescription());
								newQuiz.setQuizMaxMarks(quiz.getQuizMaxMarks());
								newQuiz.setQuizNoOfQuestions(quiz.getQuizNoOfQuestions());
								newQuiz.setQuizTitle(quiz.getQuizTitle());
								newQuiz.setNoOfAttempts(quiz.getNoOfAttempts());

								examinationCategory.setQuizzes(examinationCategory.getQuizzes());
								examination.setExaminationCategories(examination.getExaminationCategories());
								user.setExaminations(user.getExaminations());
								Quiz newCreatedQuiz = quizRepository.save(newQuiz);
								examCategoryRepository.save(examinationCategory);
								examinationRepository.save(examination);
								userRepository.save(user);
								return newCreatedQuiz;
							}
						}
					}
				}
			}
		}
//	}
		return null;
	}

	@Override
	public void deleteQuiz(String quizName, String examCategoryName, String examName) throws Exception {
		// TODO Auto-generated method stub
		int count = 0;
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();

		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				count++;
			}
		}
		if (count == 0) {
			throw new Exception("exam not found");
		} else {

			for (Examination examination : examinations) {
				if (examination.getName().equals(examName)) {
					for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
						if (examinationCategory.getCategoryTitle().equals(examCategoryName)) {
							for (Quiz newQuiz : examinationCategory.getQuizzes()) {
								if (newQuiz.getQuizTitle().equals(quizName)) {
									for (Questions qs : newQuiz.getQuestions()) {
										questionsRepository.delete(qs);
									}
									examinationCategory.getQuizzes().remove(newQuiz);
									examinationCategory.setQuizzes(examinationCategory.getQuizzes());
									examination.setExaminationCategories(examination.getExaminationCategories());
									user.setExaminations(user.getExaminations());
									quizRepository.delete(newQuiz);
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

	@Override
	public Set<Quiz> getQuizs(String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				for (ExaminationCategory examinationCategorys : examination.getExaminationCategories()) {
					if (examinationCategorys.getCategoryTitle().equals(examCategoryName)) {
						return examinationCategorys.getQuizzes();
					}
				}
			}
		}
		return null;
	}

	@Override
	public Quiz getQuiz(String quizName, String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if (examinationCategory.getCategoryTitle().equals(examCategoryName)) {
						for (Quiz newQuiz : examinationCategory.getQuizzes()) {
							if (newQuiz.getQuizTitle().equals(quizName)) {
								return newQuiz;
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Set<Questions> getQuestionsOfQuiz(String quizName, String examCategoryName, String examName) {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				for (ExaminationCategory examinationCategory : examination.getExaminationCategories()) {
					if (examinationCategory.getCategoryTitle().equals(examCategoryName)) {
						for (Quiz newQuiz : examinationCategory.getQuizzes()) {
							if (newQuiz.getQuizTitle().equals(quizName)) {
								return newQuiz.getQuestions();
							}
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public Set<Questions> getQuestionOfQuiz(String quizId) {
		// TODO Auto-generated method stub
		Optional<Quiz> newQuiz = quizRepository.findById(quizId);
		Quiz quiz = newQuiz.orElseThrow();
		return quiz.getQuestions();
	}

	@Override
	public Quiz getExactQuiz(String quizId) {
		// TODO Auto-generated method stub
		Optional<Quiz> newQuiz = quizRepository.findById(quizId);
		Quiz quiz = newQuiz.orElseThrow();
		return quiz;
	}

}

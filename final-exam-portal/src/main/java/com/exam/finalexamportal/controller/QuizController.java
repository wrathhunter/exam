package com.exam.finalexamportal.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;
import com.exam.finalexamportal.repository.ExamCategoryRepository;
import com.exam.finalexamportal.repository.ExaminationRepository;
import com.exam.finalexamportal.repository.QuizRepository;
import com.exam.finalexamportal.repository.UserRepository;
import com.exam.finalexamportal.service.QuestionService;
import com.exam.finalexamportal.service.QuizService;
import com.exam.finalexamportal.service.UsersDetailsServiceIMPL;

@CrossOrigin
@RestController
@RequestMapping(value = "/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	QuestionService questionService;
	@Autowired
	QuizRepository quizRepository;
	@Autowired
	ExaminationRepository examinationRepository;
	@Autowired
	ExamCategoryRepository examCategoryRepository;
	@Autowired
	private UsersDetailsServiceIMPL userDetailsServiceImpl;

	@PostMapping(value = "/createquiz/{examCategoryName}/{examName}")
	public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz,
			@PathVariable("examCategoryName") String examCategoryName, @PathVariable("examName") String examName) {
		Quiz newQuiz = quizService.addQuiz(quiz, examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}

	@PutMapping(value = "/updatequiz/{examCategoryName}/{examName}")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz,
			@PathVariable("examCategoryName") String examCategoryName, @PathVariable("examName") String examName)
			throws Exception {
		Quiz newQuiz = quizService.updateQuiz(quiz, examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}

	@DeleteMapping(value = "/deletequiz/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> deleteQuiz(@PathVariable("quizname") String quizName,
			@PathVariable("examCategoryName") String examCategoryName, @PathVariable("examName") String examName) throws Exception {
		quizService.deleteQuiz(quizName, examCategoryName, examName);
		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping(value = "/getallquiz/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuizs(@PathVariable("examCategoryName") String examCategoryName,
			@PathVariable("examName") String examName) {
		Set<Quiz> newQuiz = quizService.getQuizs(examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}

	@GetMapping(value = "/getonequiz/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuiz(@PathVariable("quizname") String quizName,
			@PathVariable("examCategoryName") String examCategoryName, @PathVariable("examName") String examName) {
		Quiz newQuiz = quizService.getQuiz(quizName, examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}

	@GetMapping(value = "/getquestion/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuestions(@PathVariable("quizname") String quizName,
			@PathVariable("examCategoryName") String examCategoryName, @PathVariable("examName") String examName) {
		Set<Questions> newQuestions = quizService.getQuestionsOfQuiz(quizName, examCategoryName, examName);
		List<Questions> newList = newQuestions.stream().collect(Collectors.toList());
		java.util.Collections.shuffle(newList);
		return ResponseEntity.ok(newList);
	}

	@GetMapping(value = "/getquestion/{quizid}")
	public ResponseEntity<?> getQuestion(@PathVariable("quizid") String quizid) {
		Set<Questions> newQuestions = quizService.getQuestionOfQuiz(quizid);
		List<Questions> newList = newQuestions.stream().collect(Collectors.toList());
		newList.forEach((q) -> {
			q.setAnswer("");
		});
		java.util.Collections.shuffle(newList);
		return ResponseEntity.ok(newList);
	}

	@GetMapping(value = "/getexactquiz/{quizid}")
	public ResponseEntity<?> getexactquiz(@PathVariable("quizid") String quizid) {
		Quiz quiz = quizService.getExactQuiz(quizid);
		return ResponseEntity.ok(quiz);
	}
	
	@GetMapping(value = "/getquizzesofcreater/{createrid}")
	public ResponseEntity<?> getquizzesofcreater(@PathVariable("createrid") String createrid) {
		List<Quiz> quizList = quizService.getCreatersQuizzes(createrid);
		return ResponseEntity.ok(quizList);
	}

	@PostMapping(value = "/evalquiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Questions> questions) throws Exception {
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User loggedinuser = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
//		if(loggedinuser.getIdOfAppearedQuizes()==null)
//		{
//			loggedinuser.setIdOfAppearedQuizes();
//		}
		
		Double marksGot = (double) 0;
		Integer correctAnswers = 0;
		Integer attempted = 0;
		String creatorId = null;
		String quizId = null;
		String examId = null;
		String categoryId = null;
		Integer noOfAttemptsOnQuizInteger=0;
		for (Questions q : questions) {
			creatorId = q.getCreaterId();
			quizId = q.getQuizIdOfTheQuestion();
			examId = q.getExamIdOfTheQuestion();
			categoryId = q.getCategoryIdOfTheQuestion();
			break;
		}
		loggedinuser.getIdOfAppearedQuizes().add(quizId);
		loggedinuser.setIdOfAppearedQuizes(loggedinuser.getIdOfAppearedQuizes());
		userRepository.save(loggedinuser);
		

		Optional<Quiz> quizOptional = quizRepository.findById(quizId);
		Quiz quiz = quizOptional.orElseThrow();
		if (quiz.getNoOfAttempts() == null) {
			quiz.setNoOfAttempts(0);
		}
		noOfAttemptsOnQuizInteger = quiz.getNoOfAttempts() + 1;

		Optional<ExaminationCategory> categoryOptional = examCategoryRepository.findById(categoryId);
		ExaminationCategory category = categoryOptional.orElseThrow();
		Optional<Examination> examOptional = examinationRepository.findById(examId);
		Examination exam = examOptional.orElseThrow();
		Optional<User> userOptional = userRepository.findById(creatorId);
		User user=userOptional.orElseThrow();

		quiz.setNoOfAttempts(noOfAttemptsOnQuizInteger);
		category.setQuizzes(category.getQuizzes());
		exam.setExaminationCategories(exam.getExaminationCategories());
		user.setExaminations(user.getExaminations());
		Quiz updatedQuiz = quizRepository.save(quiz);
		examCategoryRepository.save(category);
		examinationRepository.save(exam);
		userRepository.save(user);



//		Optional<Examination> examOptional=examinationRepository.findById(examId);
//		Examination exam=examOptional.orElseThrow();
//		Optional<ExaminationCategory> categoryOptional=examCategoryRepository.findById(categoryId);
//		ExaminationCategory category=categoryOptional.orElseThrow();
//		quizRepository.save(quiz);
//		
//		
//		
//		category.setQuizzes(category.getQuizzes());
//		exam.setExaminationCategories(exam.getExaminationCategories());
//		user.setExaminations(user.getExaminations());
//		quizRepository.save(quiz);
//		examCategoryRepository.save(category);
//		examinationRepository.save(exam);
//		userRepository.save(user);

		System.out.println("creator id" + creatorId);
		System.out.println("quiz id" + quizId);
		System.out.println("quiz attempts"+updatedQuiz.getNoOfAttempts());

		for (Questions q : questions) {
			Questions questionss = this.questionService.getOneQuestion(q.getQuestionId());
			if (questionss.getAnswer().equals(q.getGivenanswer())) {
				correctAnswers++;
				Integer marksSingle = Integer.parseInt(q.getQuizmaxmarks()) / questions.size();
				marksGot += marksSingle;
			}
			if (q.getGivenanswer() != null) {
				attempted++;
			}
		}

		Map<String, Object> mapObject = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted",
				attempted,"noOfAttemptsOnQuizInteger",noOfAttemptsOnQuizInteger);
		return ResponseEntity.ok(mapObject);
	}

}

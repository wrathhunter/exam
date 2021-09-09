package com.exam.finalexamportal.controller;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;

import com.exam.finalexamportal.service.QuizService;

import io.jsonwebtoken.lang.Collections;

@CrossOrigin
@RestController
@RequestMapping(value = "/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	@PostMapping(value = "/createquiz/{examCategoryName}/{examName}")
	public ResponseEntity<?> createQuiz(@RequestBody Quiz quiz,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Quiz newQuiz=quizService.addQuiz(quiz, examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}
	@PutMapping(value = "/updatequiz/{examCategoryName}/{examName}")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Quiz newQuiz=quizService.updateQuiz(quiz, examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}
	@DeleteMapping(value = "/deletequiz/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> deleteQuiz(@PathVariable("quizname") String quizName,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		quizService.deleteQuiz(quizName, examCategoryName, examName);
		return ResponseEntity.ok("quiz removed");
	}
	@GetMapping(value = "/getallquiz/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuizs(@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Set<Quiz> newQuiz=quizService.getQuizs(examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}
	@GetMapping(value = "/getonequiz/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuiz(@PathVariable("quizname") String quizName,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Quiz newQuiz=quizService.getQuiz(quizName, examCategoryName, examName);
		return ResponseEntity.ok(newQuiz);
	}
	@GetMapping(value = "/getquestion/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuestions(@PathVariable("quizname") String quizName,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Set<Questions> newQuestions=quizService.getQuestionsOfQuiz(quizName, examCategoryName, examName);
		 List<Questions> newList = newQuestions.stream().collect(Collectors.toList());
		java.util.Collections.shuffle(newList);
		return ResponseEntity.ok(newList);
	}
	@GetMapping(value = "/getquestion/{quizid}")
	public ResponseEntity<?> getQuestion(@PathVariable("quizid") String quizid)  {
		Set<Questions> newQuestions=quizService.getQuestionOfQuiz(quizid);
		List<Questions> newList = newQuestions.stream().collect(Collectors.toList());
		java.util.Collections.shuffle(newList);
		return ResponseEntity.ok(newList);
	}
	@GetMapping(value = "/getexactquiz/{quizid}")
	public ResponseEntity<?> getexactquiz(@PathVariable("quizid") String quizid)  {
		Quiz quiz=quizService.getExactQuiz(quizid);
		return ResponseEntity.ok(quiz);
	}
	
}
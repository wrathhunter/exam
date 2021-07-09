package com.exam.finalexamportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;
import com.exam.finalexamportal.service.QuestionService;
import com.exam.finalexamportal.service.QuizService;

@CrossOrigin
@RestController
@RequestMapping(value = "/question")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	@PostMapping(value = "/createquestion/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> createQuestion(@RequestBody Questions question,@PathVariable("quizname") String quiz,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Questions newQuestion=questionService.createQuestion(question, quiz, examCategoryName, examName);
		return ResponseEntity.ok(newQuestion);
	}
	@PutMapping(value = "/updatequestion/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> updateQuestion(@RequestBody Questions question,@PathVariable("quizname") String quiz,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Questions newQuestion=questionService.updateQuestion(question, quiz, examCategoryName, examName);
		return ResponseEntity.ok(newQuestion);
	}
	@DeleteMapping(value = "/deletequestion/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> deleteQuestion(@RequestBody Questions question,@PathVariable("quizname") String quizName,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		questionService.deleteQuestion(question, quizName, examCategoryName, examName);
		return ResponseEntity.ok("question removed");
	}
}

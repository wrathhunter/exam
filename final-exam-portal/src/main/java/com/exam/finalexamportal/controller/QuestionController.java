package com.exam.finalexamportal.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	@DeleteMapping(value = "/deletequestion/{questionid}/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> deleteQuestion(@PathVariable("questionid") String questionId,@PathVariable("quizname") String quizName,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName) throws Exception  {
		questionService.deleteQuestion(questionId, quizName, examCategoryName, examName);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	@GetMapping(value = "/getquestions/{quizname}/{examCategoryName}/{examName}")
	public ResponseEntity<?> getQuestion(@PathVariable("quizname") String quizname,@PathVariable("examCategoryName") String examCategoryName,@PathVariable("examName") String examName)  {
		Set<Questions> Questions=questionService.getQuestions(quizname, examCategoryName, examName);
		return ResponseEntity.ok(Questions);
	}
}

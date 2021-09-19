package com.exam.finalexamportal.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.repository.ExaminationRepository;
import com.exam.finalexamportal.service.ExaminationService;
@CrossOrigin
@RestController
@RequestMapping(value = "/exam")
public class ExaminationController {
	@Autowired
	ExaminationService examinationService;
	@Autowired
	ExaminationRepository examinationRepository;
	
	@PostMapping(value = "/createexam")
	public ResponseEntity<?> createExam(@RequestBody Examination examination) throws Exception {
		Examination newExamination=examinationService.creatExamination(examination);
		return ResponseEntity.ok(newExamination);
	}
	@GetMapping(value = "/getexam")
	public ResponseEntity<?> getExam() throws Exception {
		Set<Examination> exams=examinationService.getExamination();
		return ResponseEntity.ok(exams);
	}
	@PutMapping(value = "/putexam")
	public ResponseEntity<?> updateExam(@RequestBody Examination examination) throws Exception {
		Examination newExamination=examinationService.updateExamination(examination);
		return ResponseEntity.ok(newExamination);
	}
	@DeleteMapping(value = "/deleteexam/{examId}")
	public ResponseEntity<?> deleteExam(@PathVariable("examId") String examId ) throws Exception {
		examinationService.deleteExamination(examId);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
}

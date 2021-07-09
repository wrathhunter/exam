package com.exam.finalexamportal.controller;

import java.util.Set;

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

import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.service.ExaminationCategoryService;

@CrossOrigin
@RestController
@RequestMapping(value = "/examcategory")
public class ExaminationCategoryController {
	@Autowired
	ExaminationCategoryService examinationCategoryService;
	@PostMapping(value = "/createexamcategory/{examName}")
	public ResponseEntity<?> createExamCategory(@RequestBody ExaminationCategory examCategory,@PathVariable("examName") String examName) throws Exception {
		ExaminationCategory newExamCategory=examinationCategoryService.createCategory(examCategory, examName);
		return ResponseEntity.ok(newExamCategory);
	}
	@GetMapping(value = "/getexamcategory/{examName}")
	public ResponseEntity<?> getExamCategory(@PathVariable("examName") String examName) throws Exception {
		Set<ExaminationCategory> examCategories=examinationCategoryService.getCategories(examName);
		return ResponseEntity.ok(examCategories);
	}
	@PutMapping(value = "/putexamcategory/{examName}")
	public ResponseEntity<?> updateExamCategory(@RequestBody ExaminationCategory examCategory,@PathVariable("examName") String examName) throws Exception {
		ExaminationCategory updatedExamCategory=examinationCategoryService.updateCategory(examCategory, examName);
		return ResponseEntity.ok(updatedExamCategory);
	}
	@DeleteMapping(value = "/deleteexamcategory/{examName}")
	public ResponseEntity<?> deleteExamCategory(@RequestBody ExaminationCategory examCategory,@PathVariable("examName") String examName) throws Exception {
		examinationCategoryService.deleteCategory(examCategory, examName);
		return ResponseEntity.ok("deleted category");
	}
}

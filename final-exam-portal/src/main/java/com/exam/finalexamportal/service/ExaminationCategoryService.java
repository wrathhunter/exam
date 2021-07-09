package com.exam.finalexamportal.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Quiz;



public interface ExaminationCategoryService {
	public ExaminationCategory createCategory(ExaminationCategory examinationCategory,String examinationName) throws Exception;
	public ExaminationCategory updateCategory(ExaminationCategory examinationCategory,String examinationName) throws Exception;
	public Set<ExaminationCategory> getCategories(String examName) throws Exception;
	public void deleteCategory(ExaminationCategory examinationCategory,String examinationName)throws Exception;
}

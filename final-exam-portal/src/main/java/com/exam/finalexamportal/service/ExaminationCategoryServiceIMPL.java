package com.exam.finalexamportal.service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.ThrowableCauseExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Quiz;
import com.exam.finalexamportal.repository.ExamCategoryRepository;
import com.exam.finalexamportal.repository.ExaminationRepository;
import com.exam.finalexamportal.repository.UserRepository;

@Service(value = "examinationCategoryService")
@Transactional
public class ExaminationCategoryServiceIMPL implements ExaminationCategoryService {
	@Autowired
	private UsersDetailsServiceIMPL userDetailsServiceImpl;
	@Autowired
	private ExamCategoryRepository examCategoryRepository;
	@Autowired
	private ExaminationRepository examinationRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public ExaminationCategory createCategory(ExaminationCategory examinationCategory, String examinationName)
			throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examinationName)) {
				count++;
			}
		}
		if(count==0)
		{
			throw new Exception("exam not found");
		}
		else {
		for (Examination examination : examinations) {
			if (examination.getName().equals(examinationName)) {
				if (examination.getExaminationCategories().size() == 0) {
					ExaminationCategory newCategory = new ExaminationCategory();
					newCategory.setCategoryDescription(examinationCategory.getCategoryDescription());
					newCategory.setCategoryTitle(examinationCategory.getCategoryTitle());
					newCategory.setQuizzes(examinationCategory.getQuizzes());
					examination.getExaminationCategories().add(newCategory);
					examination.setExaminationCategories(examination.getExaminationCategories());
					ExaminationCategory newCreatedCategory = examCategoryRepository.save(newCategory);
					examinationRepository.save(examination);
					userRepository.save(user);

					return newCreatedCategory;
				} 
				else {

					for (ExaminationCategory category : examination.getExaminationCategories()) {

						if (category.getCategoryTitle().equals(examinationCategory.getCategoryTitle())) {
							throw new Exception("Category already exists");

						} else {
							ExaminationCategory newCategory = new ExaminationCategory();
							newCategory.setCategoryDescription(examinationCategory.getCategoryDescription());
							newCategory.setCategoryTitle(examinationCategory.getCategoryTitle());
							newCategory.setQuizzes(examinationCategory.getQuizzes());
							examination.getExaminationCategories().add(newCategory);
							examination.setExaminationCategories(examination.getExaminationCategories());
							ExaminationCategory newCreatedCategory = examCategoryRepository.save(newCategory);
							examinationRepository.save(examination);
							userRepository.save(user);

							return newCreatedCategory;
						}

					}
				}
			} 
		}
		}
		return null;
	}

	@Override
	public ExaminationCategory updateCategory(ExaminationCategory examinationCategory, String examinationName)
			throws Exception {
		// TODO Auto-generated method stub
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examinationName)) {
				for (ExaminationCategory category : examination.getExaminationCategories()) {
					if (category.getCategoryTitle().equals(examinationCategory.getCategoryTitle())) {
						category.setCategoryDescription(examinationCategory.getCategoryDescription());
						category.setCategoryTitle(examinationCategory.getCategoryTitle());
						category.setQuizzes(examinationCategory.getQuizzes());
						examination.setExaminationCategories(examination.getExaminationCategories());
						ExaminationCategory newCreatedCategory = examCategoryRepository.save(category);
						examinationRepository.save(examination);
						userRepository.save(user);
						return newCreatedCategory;
					} 

				}
			} 
		}
		return null;
	}

	@Override
	public Set<ExaminationCategory> getCategories(String examName) throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				count++;
			}
		}
		if(count==0)
		{
			throw new Exception("exam not found");
		}
		else {
		for (Examination examination : examinations) {
			if (examination.getName().equals(examName)) {
				return examination.getExaminationCategories();
			} 
		}
		}
		return null;
	}

	@Override
	public void deleteCategory(ExaminationCategory examinationCategory, String examinationName) throws Exception {
		// TODO Auto-generated method stub
		int count=0;
		String principal = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = (User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> examinations = user.getExaminations();
		for (Examination examination : examinations) {
			if (examination.getName().equals(examinationName)) {
				count++;
			}
		}
		if(count==0)
		{
			throw new Exception("exam not found");
		}
		else {
		for (Examination examination : examinations) {
			if (examination.getName().equals(examinationName)) {
				for (ExaminationCategory category : examination.getExaminationCategories()) {
					if (category.getCategoryTitle().equals(examinationCategory.getCategoryTitle())) {
						examination.getExaminationCategories().remove(category);
						examination.setExaminationCategories(examination.getExaminationCategories());
						examCategoryRepository.delete(category);
						examinationRepository.save(examination);
						userRepository.save(user);
					} else {
						throw new Exception("Category does not exists");
					}

				}
			}
		}
		}

	}

}

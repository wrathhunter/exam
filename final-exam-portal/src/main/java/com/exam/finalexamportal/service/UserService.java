package com.exam.finalexamportal.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;

public interface UserService {
	public User CreateUser(User user) throws Exception;
	public User getUser(String username) throws Exception;
	public void deleteUser(String username) throws Exception;
	public User updateUser(User user) throws Exception;
//	public Set<Examination> getAllExams();
//	public Examination createExams(Examination examination);
	
}
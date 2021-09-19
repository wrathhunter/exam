package com.exam.finalexamportal.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;

public interface ExaminationService {
	public Examination creatExamination(Examination examination) throws Exception;
	public Examination updateExamination(Examination examination) throws Exception;
	public void deleteExamination(String examinationId) throws Exception;
	public Set<Examination> getExamination();
	
}

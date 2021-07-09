package com.exam.finalexamportal.service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.repository.ExaminationRepository;
import com.exam.finalexamportal.repository.UserRepository;
@Service(value = "examinationService")
@Transactional
public class ExaminationServiceIMPL implements ExaminationService {
	@Autowired
	private ExaminationRepository examinationRepository;
	@Autowired
	private UsersDetailsServiceIMPL userDetailsServiceImpl;
	@Autowired
	private UserRepository userRepository;
	


	@Override
	public Examination creatExamination(Examination examination) throws Exception {
		// TODO Auto-generated method stub
		String principal=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=(User) this.userDetailsServiceImpl.loadUserByUsername(principal);	
		Set<Examination> exams=user.getExaminations();
		for (Examination examination2 : exams) {
			if(examination2.getName().equals(examination.getName()))
			{
				throw new Exception("exam already present");
			}
		}
		Examination newExam=new Examination(examination.getName(),examination.getExaminationDescription());
		exams.add(newExam);
		user.setExaminations(exams);
		Examination examinations=examinationRepository.save(newExam);
		userRepository.save(user);
		return examinations;
	}

	@Override
	public Examination updateExamination(Examination examination) throws Exception {
		// TODO Auto-generated method stub
		String principal=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=(User) this.userDetailsServiceImpl.loadUserByUsername(principal);	
		Set<Examination> exams=user.getExaminations();
		for (Examination examination2 : exams) {
			if(examination2.getName().equals(examination.getName()))
			{
				examination2.setName(examination.getName());
				examination2.setExaminationDescription(examination.getExaminationDescription());
				examination2.setExaminationCategories(examination.getExaminationCategories());
				//exams.add(examination2);
				user.setExaminations(exams);
				Examination examinations=examinationRepository.save(examination2);
				userRepository.save(user);
				return examinations;
			}
			else {
				throw new Exception("exam not present");
			}
		}
		return null;
	}

	@Override
	public void deleteExamination(Examination examination) throws Exception {
		// TODO Auto-generated method stub
		String principal=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=(User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> exams=user.getExaminations();
		for (Examination examination2 : exams) {
			if(examination2.getName().equals(examination.getName())) {
				exams.remove(examination2);
				user.setExaminations(exams);
				examinationRepository.delete(examination2);	
				userRepository.save(user);
			}
		}
		
		
	}
	@Override
	public Set<Examination> getExamination() {
		// TODO Auto-generated method stub
		String principal=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=(User) this.userDetailsServiceImpl.loadUserByUsername(principal);
		Set<Examination> exams=user.getExaminations();
		return exams;
		
	}
	

	
	
}

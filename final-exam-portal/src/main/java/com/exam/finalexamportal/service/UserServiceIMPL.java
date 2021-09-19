package com.exam.finalexamportal.service;

import java.security.Principal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.finalexamportal.model.Role;
import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;
import com.exam.finalexamportal.model.exam.Questions;
import com.exam.finalexamportal.model.exam.Quiz;
import com.exam.finalexamportal.repository.ExamCategoryRepository;
import com.exam.finalexamportal.repository.ExaminationRepository;
import com.exam.finalexamportal.repository.QuestionsRepository;
import com.exam.finalexamportal.repository.QuizRepository;
import com.exam.finalexamportal.repository.RoleRepository;
import com.exam.finalexamportal.repository.UserRepository;


@Service(value = "userService")
@Transactional
public class UserServiceIMPL implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private ExaminationRepository examinationRepository;
	@Autowired
    private ExamCategoryRepository examCategoryRepository;
	@Autowired
    private QuizRepository quizRepository;
	@Autowired
    private QuestionsRepository questionsRepository;
	
	@Autowired
	private UsersDetailsServiceIMPL userDetailsServiceImpl;

	@Override
	public User CreateUser(User user) throws Exception {
		User newUser=userRepository.findByusername(user.getUserName());
		if(newUser!=null)
		{
			throw new Exception("User already present!");
		}
		else
		{
			User urrently_Created_User=new User(user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNo(), user.isEnabled(), user.getProfile());
			Set<Role> roles = new HashSet<>();
			roles.addAll(user.getRoles());
			urrently_Created_User.setRoles(roles);
			roleRepository.saveAll(roles);
			newUser=userRepository.save(urrently_Created_User);
		}
		return newUser;
		
	}
	@Override
	public User getUser(String username) throws Exception
	{
		User newUser=userRepository.findByusername(username);
		if(newUser==null)
		{
			throw new Exception("User does not exist!");
		}
		else
		{
			return newUser;
		}
	}
	@Override
	public void deleteUser(String username) throws Exception
	{
		User newUser=userRepository.findByusername(username);
		if(newUser==null)
		{
			throw new Exception("User does not exist!");
		}
		else
		{
			userRepository.delete(newUser);
			for(Role role:newUser.getRoles())
			{
				roleRepository.delete(role);
			}
			for(Examination exam:newUser.getExaminations())
			{
				examinationRepository.delete(exam);
			}
			for(Examination exam:newUser.getExaminations())
			{
				for(ExaminationCategory cat:exam.getExaminationCategories())
				{
					examCategoryRepository.delete(cat);
				}
			}
			for(Examination exam:newUser.getExaminations())
			{
				for(ExaminationCategory cat:exam.getExaminationCategories())
				{
					for(Quiz quiz:cat.getQuizzes())
					{
						quizRepository.delete(quiz);
					}
				}
			}
			for(Examination exam:newUser.getExaminations())
			{
				for(ExaminationCategory cat:exam.getExaminationCategories())
				{
					for(Quiz quiz:cat.getQuizzes())
					{
						for(Questions question:quiz.getQuestions())
						{
							questionsRepository.delete(question);
						}					}
				}
			}
		}
	}
	@Override
	public User updateUser(User user) throws Exception
	{
		User newUser=userRepository.findByusername(user.getUserName());
		if(newUser==null)
		{
			throw new Exception("User does not exist!");
		}
		else
		{
			newUser.setEmail(user.getEmail());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setPassword(user.getPassword());
			newUser.setPhoneNo(user.getPhoneNo());
			newUser.setProfile(user.getProfile());
			Set<Role> roles = new HashSet<>();
			roles.addAll(user.getRoles());
			newUser.setRoles(roles);
			roleRepository.saveAll(roles);
			userRepository.save(newUser);
			return newUser;
		}
	}
//	@Override
//	public Set<Examination> getAllExams() {
//		String principal=SecurityContextHolder.getContext().getAuthentication().getName();
//		return userRepository.findByexaminations(principal);
//		
//	}
//	@Override
//	public Examination createExams(Examination examination) {
//		String principal=SecurityContextHolder.getContext().getAuthentication().getName();
//		User user=(User) this.userDetailsServiceImpl.loadUserByUsername(principal);
//		Set<Examination> exams=user.getExaminations();
//		exams.add(examination);
//		user.setExaminations(exams);
//		return examination;
//	}
	
}
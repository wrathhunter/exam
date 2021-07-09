package com.exam.finalexamportal.repository;

import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;

public interface UserRepository extends MongoRepository<User, String>{
	public User findByusername(String username);
	public Boolean existsByusername(String username);
	public Boolean existsByemail(String email);
	public Set<Examination> findByexaminations(String username);
}

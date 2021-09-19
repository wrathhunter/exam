package com.exam.finalexamportal.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.finalexamportal.model.EmployeeRoleRelation;
import com.exam.finalexamportal.model.Role;
import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.repository.UserRepository;
import com.exam.finalexamportal.service.ExaminationService;
import com.exam.finalexamportal.service.UserService;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserAPI {
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPaswordEncoder;
	
	@PostMapping(value = "/student")
	public User createUser(@RequestBody User user) throws Exception {
		user.setPassword(this.bCryptPaswordEncoder.encode(user.getPassword()));
		Role role = new Role();
		role.setName(EmployeeRoleRelation.ROLE_USER);
		role.setAttachedUserIdString(user.getId());
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		return userService.CreateUser(user);
	}
	@PostMapping(value = "/moderator")
	public User createModerator(@RequestBody User user) throws Exception {
		user.setPassword(this.bCryptPaswordEncoder.encode(user.getPassword()));
		Role role = new Role();
		role.setName(EmployeeRoleRelation.ROLE_MODERATOR);
		role.setAttachedUserIdString(user.getId());
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		return userService.CreateUser(user);
	}


	@GetMapping(value = "/{username}")
	public User getUser(@PathVariable("username") String username) throws Exception {

		return this.userService.getUser(username);
	}

	@DeleteMapping(value = "/{username}")
	public void deleteUser(@PathVariable("username") String username) throws Exception {
		userService.deleteUser(username);
	}

	@PutMapping(value = "/{username}")
	public User updateUser(@RequestBody User user) throws Exception {
		return userService.updateUser(user);
	}
	

}

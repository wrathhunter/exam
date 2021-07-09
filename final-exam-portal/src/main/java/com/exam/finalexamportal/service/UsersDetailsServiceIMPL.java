package com.exam.finalexamportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.repository.UserRepository;
@Service
public class UsersDetailsServiceIMPL implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepository.findByusername(username);
		if(user==null)
		{
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user found  ");
		}
		return user;
	}
	
}

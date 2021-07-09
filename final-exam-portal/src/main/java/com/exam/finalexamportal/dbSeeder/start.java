package com.exam.finalexamportal.dbSeeder;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.exam.finalexamportal.model.Authority;
import com.exam.finalexamportal.model.EmployeeRoleRelation;
import com.exam.finalexamportal.model.Role;
import com.exam.finalexamportal.model.User;
import com.exam.finalexamportal.repository.RoleRepository;
import com.exam.finalexamportal.repository.UserRepository;
import com.exam.finalexamportal.service.UserService;

@Component
public class start implements CommandLineRunner {
	
	
	@Autowired
	private UserService userService;

    @Override
    public void run(String... strings) throws Exception {
        
//        User user=new User();
//        user.setEmail("sibaprasadpanda100@gmail.com");
//        user.setEnabled(true);
//        user.setFirstName("Siba");
//        user.setLastName("Panda");
//        user.setPassword("hello");
//        user.setPhoneNo("9078124294");
//        user.setProfile("profile.jpg");
//        user.setUserName("siba007");
//        Role role=new Role();
//        role.setName(EmployeeRoleRelation.ROLE_ADMIN);
//        Set<Role> roles=new HashSet<>();
//        roles.add(role);
//        user.setRoles(roles);
//        		
//        User newUser=userService.CreateUser(user);
//        newUser.getRoles().forEach(rolee->{
//        	System.out.println(rolee.getName().name());
//		});
        
        
    }
}

package com.exam.finalexamportal.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.finalexamportal.model.EmployeeRoleRelation;
import com.exam.finalexamportal.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByname(EmployeeRoleRelation name);
}

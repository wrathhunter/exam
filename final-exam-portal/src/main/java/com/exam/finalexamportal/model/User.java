package com.exam.finalexamportal.model;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.finalexamportal.model.exam.Examination;
import com.exam.finalexamportal.model.exam.ExaminationCategory;


@Document
public class User implements UserDetails {
	@Id
	private String id;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 120)
	private String password;
	private String firstName;
	private String lastName;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	public List<String> getIdOfAppearedQuizes() {
		return idOfAppearedQuizes;
	}
	public void setIdOfAppearedQuizes(List<String> idOfAppearedQuizes) {
		this.idOfAppearedQuizes = idOfAppearedQuizes;
	}
	private String phoneNo;
	private boolean enabled=true;
	private String profile;
	private List<String> idOfAppearedQuizes=new ArrayList<String>();
	@DBRef
	private Set<Role> roles = new HashSet<>();
	
	@DBRef
	private Set<Examination> examinations=new HashSet<Examination>();	
	public User()
	{
		
	}
	public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 120) String password, String firstName,
			String lastName, @NotBlank @Size(max = 50) @Email String email, String phoneNo, boolean enabled,
			String profile) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.enabled = enabled;
		this.profile = profile;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getUserName() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<Examination> getExaminations() {
		return examinations;
	}
	public void setExaminations(Set<Examination> examinations) {
		this.examinations = examinations;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<Authority> set=new HashSet<>();
		this.roles.forEach(role->{
			set.add(new Authority(role.getName().name()));
		});
		return set;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	
	

	
	
	
}

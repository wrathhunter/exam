package com.exam.finalexamportal.model.exam;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "exams")
public class Examination {
	@Id
	private String id;
	private String name;
	private String examinationDescription;
	@DBRef
	private Set<ExaminationCategory> examinationCategories=new LinkedHashSet<ExaminationCategory>();
	
	public Examination() {
		
	}

	public Examination(String name, String examinationDescription) {
		super();
		this.name = name;
		this.examinationDescription = examinationDescription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExaminationDescription() {
		return examinationDescription;
	}

	public void setExaminationDescription(String examinationDescription) {
		this.examinationDescription = examinationDescription;
	}

	public Set<ExaminationCategory> getExaminationCategories() {
		return examinationCategories;
	}

	public void setExaminationCategories(Set<ExaminationCategory> examinationCategories) {
		this.examinationCategories = examinationCategories;
	}
	
	
	

}

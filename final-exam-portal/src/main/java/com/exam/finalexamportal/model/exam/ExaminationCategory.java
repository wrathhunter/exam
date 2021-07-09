package com.exam.finalexamportal.model.exam;


import java.util.LinkedHashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class ExaminationCategory {
	@Id
	private String categoryId;
	@NotBlank
	@Indexed(unique=true)
	private String categoryTitle;
	private String categoryDescription;
	@DBRef
	private Set<Quiz> quizzes=new LinkedHashSet<Quiz>();
	
	public ExaminationCategory() {
	}

	public ExaminationCategory(@NotBlank String categoryTitle, String categoryDescription) {
		super();
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;

	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}

	public Set<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(Set<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
	
	
}

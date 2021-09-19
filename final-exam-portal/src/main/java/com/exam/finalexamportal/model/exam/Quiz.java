package com.exam.finalexamportal.model.exam;

import java.util.HashSet;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Quiz {
	@Id
	private String quizId;
	private Boolean active;
	@NotBlank
	@Indexed(unique=true)
	private String quizTitle;


	public Integer getNoOfAttempts() {
		return noOfAttempts;
	}

	public void setNoOfAttempts(Integer noOfAttempts) {
		this.noOfAttempts = noOfAttempts;
	}

	private String quizDescription;
	private Integer quizMaxMarks;
	private Integer quizNoOfQuestions;
	private Integer noOfAttempts;

	@DBRef
	private ExaminationType examinationType;
	@DBRef
	private Set<Questions> questions=new HashSet<Questions>();
	
	public Quiz() {
	}

	public Quiz(Boolean active, @NotBlank String quizTitle, String quizDescription, Integer quizMaxMarks,
			Integer quizNoOfQuestions, ExaminationType examinationType) {
		super();
		this.active = active;
		this.quizTitle = quizTitle;
		this.quizDescription = quizDescription;
		this.quizMaxMarks = quizMaxMarks;
		this.quizNoOfQuestions = quizNoOfQuestions;
		this.examinationType = examinationType;
		
	}

	public String getQuizId() {
		return quizId;
	}


	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getQuizTitle() {
		return quizTitle;
	}

	public void setQuizTitle(String quizTitle) {
		this.quizTitle = quizTitle;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public Integer getQuizMaxMarks() {
		return quizMaxMarks;
	}

	public void setQuizMaxMarks(Integer quizMaxMarks) {
		this.quizMaxMarks = quizMaxMarks;
	}

	public Integer getQuizNoOfQuestions() {
		return quizNoOfQuestions;
	}

	public void setQuizNoOfQuestions(Integer quizNoOfQuestions) {
		this.quizNoOfQuestions = quizNoOfQuestions;
	}

	public ExaminationType getExaminationType() {
		return examinationType;
	}

	public void setExaminationType(ExaminationType examinationType) {
		this.examinationType = examinationType;
	}

	public Set<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}

	
}

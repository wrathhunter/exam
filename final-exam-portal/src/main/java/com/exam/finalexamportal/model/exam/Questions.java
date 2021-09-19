package com.exam.finalexamportal.model.exam;

import javax.validation.constraints.NotBlank;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


@Document
public class Questions {
	@Id
	private String questionId;
	@NotBlank
	private String content;
	private String image;
	private String option1;
	public String getQuizmaxmarks() {
		return quizmaxmarks;
	}
	public void setQuizmaxmarks(String quizmaxmarks) {
		this.quizmaxmarks = quizmaxmarks;
	}
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private String quizmaxmarks;
	private String createrId;
	public String getCategoryIdOfTheQuestion() {
		return categoryIdOfTheQuestion;
	}
	public void setCategoryIdOfTheQuestion(String categoryIdOfTheQuestion) {
		this.categoryIdOfTheQuestion = categoryIdOfTheQuestion;
	}
	private String quizIdOfTheQuestion;
	private String examIdOfTheQuestion;
	private String categoryIdOfTheQuestion;
	public String getExamIdOfTheQuestion() {
		return examIdOfTheQuestion;
	}
	public void setExamIdOfTheQuestion(String examIdOfTheQuestion) {
		this.examIdOfTheQuestion = examIdOfTheQuestion;
	}
	public String getQuizIdOfTheQuestion() {
		return quizIdOfTheQuestion;
	}
	public void setQuizIdOfTheQuestion(String quizIdOfTheQuestion) {
		this.quizIdOfTheQuestion = quizIdOfTheQuestion;
	}
	public String getCreaterId() {
		return createrId;
	}
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}
	@Transient
	private String givenanswer;
	public String getGivenanswer() {
		return givenanswer;
	}
	public void setGivenanswer(String givenanswer) {
		this.givenanswer = givenanswer;
	}
	public Questions() {
		
	}
	public Questions(@NotBlank String content, String image, String option1, String option2, String option3,
			String option4, String answer) {
		super();
		this.content = content;
		this.image = image;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
	
	
	
}

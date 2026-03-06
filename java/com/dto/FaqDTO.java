package com.dto;

import java.sql.Timestamp;

public class FaqDTO {

	private int faqNo;
	private int sortOrder;
	private String category;
	private String question;
	private String answer;
	private String status;
	private Timestamp regDate;

	public int getFaqNo() {
		return faqNo;
	}

	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}

	public int getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "FaqDTO [faqNo=" + faqNo + ", sortOrder=" + sortOrder + ", category=" + category + ", question="
				+ question + ", answer=" + answer + ", status=" + status + ", regDate=" + regDate + "]";
	}

}
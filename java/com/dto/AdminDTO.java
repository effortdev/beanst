package com.dto;

public class AdminDTO {

	private String userId;

	private String password;

	private String role;

	public AdminDTO() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminDTO [userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}

}
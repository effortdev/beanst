package com.service;

import com.dao.UserDAO;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class UserService {
	private UserDAO dao;

	public UserService(ServletContext context) {
		this.dao = new UserDAO(context);
	}

	public boolean joinUser(UserVO userVO) {
		return dao.insertUser(userVO);
	}

	public String findUserId(String name, String email) {
		return dao.findId(name, email);
	}

	public boolean isUserValid(String userId, String name, String email) {
		return dao.checkUserForPw(userId, name, email);
	}

	public boolean resetPassword(String userId, String newPw) {
		return dao.updatePassword(userId, newPw);
	}

	public boolean checkPassword(String userId, String password) {
		return dao.checkPassword(userId, password);
	}

	public boolean updateContact(String userId, String email, String phone) {
		return dao.updateContact(userId, email, phone);
	}

	public boolean updateMemberStatus(UserVO user) {

		return dao.updateStatus(user.getUserId(), user.getStatus());
	}

	public UserVO selectUser(String id) {
		return dao.selectUser(id);
	}

	public boolean isEmailDuplicate(String email, String userId) {
		return dao.isEmailDuplicate(email, userId);
	}
}
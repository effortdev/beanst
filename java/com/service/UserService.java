package com.service;

import jakarta.servlet.ServletContext;
import com.dao.UserDAO;
import com.vo.UserVO;

public class UserService {
	private ServletContext context;

	public UserService(ServletContext context) {
		this.context = context;
	}

	public boolean joinUser(UserVO userVO) {
		UserDAO dao = new UserDAO(context);
		return dao.insertUser(userVO);
	}

	// 아이디 찾기 서비스 로직
	public String findUserId(String name, String email) {
		UserDAO dao = new UserDAO(context);
		return dao.findId(name, email);
	}

	public boolean isUserValid(String userId, String name, String email) {
		return new UserDAO(context).checkUserForPw(userId, name, email);
	}

	public boolean resetPassword(String userId, String newPw) {
		return new UserDAO(context).updatePassword(userId, newPw);
	}
}
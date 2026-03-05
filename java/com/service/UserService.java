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
}
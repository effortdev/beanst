package com.service;

import com.dao.LoginDAO;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class LoginService {

	 private ServletContext context;

	    public LoginService(ServletContext context) {
	        this.context = context;
	    }

	    public UserVO login(String userId, String password) {
	    	LoginDAO dao = new LoginDAO(context);
	        return dao.login(userId, password);
	    }
}
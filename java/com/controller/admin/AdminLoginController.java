package com.controller.admin;

import com.controller.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminLoginController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageTitle", "관리자페이지");
		return "/admin/login";
	}
}
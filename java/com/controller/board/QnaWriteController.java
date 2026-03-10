package com.controller.board;

import com.controller.Action;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaWriteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginMember") == null) {
			return "redirect:/login/login.do";
		}

		UserVO loginMember = (UserVO) session.getAttribute("loginMember");
		request.setAttribute("loginMember", loginMember);

		request.setAttribute("pageTitle", "Q&A 작성");
		request.setAttribute("pageCss", "board");
		request.setAttribute("contentPage", "/WEB-INF/views/board/qna/write.jsp");

		return "board/qna/write";
	}
}
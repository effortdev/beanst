package com.controller.board;

import com.controller.Action;
import com.dao.QnaDAO;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaInsertController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		if (!"POST".equalsIgnoreCase(request.getMethod())) {
			return "redirect:/qnaList.do";
		}

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginMember") == null) {
			return "redirect:/login/login.do";
		}

		UserVO loginMember = (UserVO) session.getAttribute("loginMember");
		String userId = loginMember.getUserId();

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		QnaDAO dao = new QnaDAO(request.getServletContext());
		int result = dao.insertQna(userId, title, content);

		if (result > 0) {
			return "redirect:/qnaList.do";
		} else {
			request.setAttribute("errorMsg", "문의 등록에 실패했습니다. 잠시 후 다시 시도해주세요.");
			request.setAttribute("pageTitle", "Q&A 작성");
			request.setAttribute("pageCss", "board");
			return "board/qna/write";
		}
	}
}

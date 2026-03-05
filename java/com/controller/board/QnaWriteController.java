package com.controller.board;

import com.controller.Action;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaWriteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		// 1. 로그인 체크 (QnA 목록과 동일한 정책)
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginMember") == null) {
			return "redirect:/login/login.do";
		}

		UserVO loginMember = (UserVO) session.getAttribute("loginMember");
		request.setAttribute("loginMember", loginMember);

		// 2. 페이지 공통 속성 설정 (레이아웃에서 사용)
		request.setAttribute("pageTitle", "Q&A 작성");
		request.setAttribute("pageCss", "board");
		request.setAttribute("contentPage", "/WEB-INF/views/board/qna/write.jsp");

		// 3. 논리 뷰 이름 반환 (다른 QnA 페이지와 동일 패턴)
		return "board/qna/write";
	}
}
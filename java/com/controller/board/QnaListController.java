package com.controller.board;

import java.util.List;
import com.util.PageInfo;

import com.controller.Action;
import com.dao.QnaDAO;
import com.dto.QnaDTO;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("loginMember") == null) {
			return "redirect:/login/login.do";
		}

		UserVO loginMember = (UserVO) session.getAttribute("loginMember");
		String userId = loginMember.getUserId();

		String pageParam = request.getParameter("page");
		int currentPage = 1;
		if (pageParam != null && !pageParam.isEmpty()) {
			try {
				currentPage = Integer.parseInt(pageParam);
			} catch (NumberFormatException ignore) {
			}
		}

		String keyword = request.getParameter("keyword");

		QnaDAO dao = new QnaDAO(request.getServletContext());

		int listCount = dao.getQnaCount(keyword, userId);

		PageInfo pi = new PageInfo(currentPage, listCount, 5, 5);

		List<QnaDTO> list = dao.selectQnaList(pi, keyword, userId);

		request.setAttribute("pageTitle", "Q&A");
		request.setAttribute("keyword", keyword);
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("pageCss", "board");
		request.setAttribute("contentPage", "/WEB-INF/views/board/qna/list.jsp");

		return "board/qna/list";
	}
}
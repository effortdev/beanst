package com.controller.board;

import java.util.List;

import com.controller.Action;
import com.dao.QnaDAO;
import com.dto.QnaDTO;
import com.util.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String pageParam = request.getParameter("page");
		int currentPage = 1;
		if (pageParam != null && !pageParam.isEmpty()) {
			try {
				currentPage = Integer.parseInt(pageParam);
			} catch (NumberFormatException ignore) {
			}
		}

		String keyword = request.getParameter("keyword");

		QnaDAO dao = new QnaDAO();

		int listCount = dao.getQnaCount(keyword);

		PageInfo pi = new PageInfo(currentPage, listCount, 5, 10);

		List<QnaDTO> list = dao.selectQnaList(pi, keyword);
		
		request.setAttribute("pageTitle",  "Q&A");
		request.setAttribute("keyword", keyword);
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		request.setAttribute("pageCss", "style");
		request.setAttribute("contentPage", "/WEB-INF/views/board/qna/list.jsp");

		return "board/qna/list";
	}
}
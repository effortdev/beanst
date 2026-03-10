package com.controller.board;

import com.controller.Action;
import com.dao.QnaDAO;
import com.dto.QnaDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaDetailController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String noParam = request.getParameter("no");

		if (noParam == null || noParam.isEmpty()) {
			return "redirect:/qnaList.do";
		}

		int no;
		try {
			no = Integer.parseInt(noParam);
		} catch (NumberFormatException e) {
			return "redirect:/qnaList.do";
		}

		QnaDAO dao = new QnaDAO(request.getServletContext());
		QnaDTO qna = dao.selectQna(no);

		if (qna == null) {
			return "redirect:/qnaList.do";
		}

		request.setAttribute("qna", qna);
		request.setAttribute("pageTitle", "Q&A 상세");
		request.setAttribute("pageCss", "board");
		request.setAttribute("contentPage", "/WEB-INF/views/board/qna/detail.jsp");

		return "board/qna/detail";
	}
}
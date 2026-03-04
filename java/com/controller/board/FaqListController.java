package com.controller.board;

import java.util.List;

import com.controller.Action;
import com.dao.FaqDAO;
import com.dto.FaqDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FaqListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String category = request.getParameter("category");

		FaqDAO dao = new FaqDAO();
		List<FaqDTO> faqList = dao.selectFaqList(category);
		request.setAttribute("pageTitle",  "FAQ");
		request.setAttribute("faqList", faqList);
		request.setAttribute("category", category);
		request.setAttribute("pageCss", "style");
//		String pageCss = (String) request.getAttribute("pageCss");
//		System.out.println("가져온 값: " + pageCss);
		return "board/faq/list";
	}
}
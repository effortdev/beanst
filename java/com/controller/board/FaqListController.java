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

		// AdminLoginService → AdminDAO 패턴과 동일하게
		// ServletContext를 넘겨서 Mapper XML을 로드하도록 변경
		FaqDAO dao = new FaqDAO(request.getServletContext());
		List<FaqDTO> faqList = dao.selectFaqList(category);
		request.setAttribute("pageTitle", "FAQ");
		request.setAttribute("faqList", faqList);
		request.setAttribute("category", category);
		request.setAttribute("pageCss", "board");
//		String pageCss = (String) request.getAttribute("pageCss");
//		System.out.println("가져온 값: " + pageCss);
		return "board/faq/list";
	}
}
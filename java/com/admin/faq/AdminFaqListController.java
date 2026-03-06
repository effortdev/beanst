package com.admin.faq;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFaqListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		request.setAttribute("pageCss", "admin-faq");

		Connection conn = null;

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminFaqDAO dao = new AdminFaqDAO(context);

			List<AdminFaqDTO> list = dao.selectFaqList(conn);

			request.setAttribute("faqList", list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/faq/faqList";
	}
}
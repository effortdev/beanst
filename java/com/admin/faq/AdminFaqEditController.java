package com.admin.faq;

import static com.util.JdbcUtil.*;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFaqEditController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin-faq");

		Connection conn = null;

		try {

			int faqNo = Integer.parseInt(request.getParameter("faq_no"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminFaqDAO dao = new AdminFaqDAO(context);

			AdminFaqDTO faq = dao.selectFaq(conn, faqNo);

			request.setAttribute("faq", faq);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/faq/edit";
	}
}
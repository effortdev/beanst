package com.admin.faq;

import static com.util.JdbcUtil.*;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFaqDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int faqNo = Integer.parseInt(request.getParameter("faq_no"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminFaqDAO dao = new AdminFaqDAO(context);

			int result = dao.deleteFaq(conn, faqNo);

			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}

		return "redirect:/admin/faq/list.do";
	}
}
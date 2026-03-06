package com.admin.faq;

import static com.util.JdbcUtil.*;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFaqInsertController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			String category = request.getParameter("category");
			String question = request.getParameter("question");
			String answer = request.getParameter("answer");
			int sortOrder = Integer.parseInt(request.getParameter("sort_order"));
			String status = request.getParameter("status");

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminFaqDAO dao = new AdminFaqDAO(context);

			AdminFaqDTO faq = new AdminFaqDTO();

			faq.setCategory(category);
			faq.setQuestion(question);
			faq.setAnswer(answer);
			faq.setSortOrder(sortOrder);
			faq.setStatus(status);

			int result = dao.insertFaq(conn, faq);

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
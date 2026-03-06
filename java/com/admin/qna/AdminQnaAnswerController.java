package com.admin.qna;

import static com.util.JdbcUtil.*;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaAnswerController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;


		try {

			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));
			String answer = request.getParameter("answer");

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminQnaDAO dao = new AdminQnaDAO(context);

			int result = dao.updateAnswer(conn, answer, qnaNo);

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

		return "redirect:/admin/qna/detail.do?qna_no=" + request.getParameter("qna_no");
	}
}
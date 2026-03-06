package com.admin.qna;

import static com.util.JdbcUtil.*;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminQnaDAO dao = new AdminQnaDAO(context);

			int result = dao.deleteQna(conn, qnaNo);

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

		return "redirect:/admin/qna/list.do";
	}
}
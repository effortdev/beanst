package com.admin.qna;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;

import com.controller.Action;
import com.dto.QnaDTO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaDetailController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int qnaNo = Integer.parseInt(request.getParameter("qna_no"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminQnaDAO dao = new AdminQnaDAO(context);

			QnaDTO qna = dao.selectQnaDetail(conn, qnaNo);

			request.setAttribute("qna", qna);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/qna/qnaDetail";
	}
}
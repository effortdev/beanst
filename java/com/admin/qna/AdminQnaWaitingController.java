package com.admin.qna;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaWaitingController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;
		request.setAttribute("pageCss", "admin-qna");

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminQnaDAO dao = new AdminQnaDAO(context);

			List<AdminQnaDTO> list = dao.selectWaitingList(conn);

			request.setAttribute("qnaList", list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/qna/qnaList";
	}
}
package com.admin.qna;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.dto.QnaDTO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			conn = getConnection();

			// ServletContext 가져오기
			ServletContext context = request.getServletContext();

			AdminQnaDAO dao = new AdminQnaDAO(context);

			List<QnaDTO> list = dao.getQnaList(conn);

			request.setAttribute("qnaList", list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/qna/qnaList";
	}
}
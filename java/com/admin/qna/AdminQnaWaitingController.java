package com.admin.qna;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.util.PageInfo;

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

			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			int listCount = dao.selectWaitingCount(conn);

			int pageLimit = 10;
			int boardLimit = 10;

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			List<AdminQnaDTO> list = dao.selectWaitingList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

			request.setAttribute("qnaList", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageUrl", "/admin/qna/waiting.do");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/qna/qnaList";
	}
}
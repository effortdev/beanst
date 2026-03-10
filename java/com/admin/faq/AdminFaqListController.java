package com.admin.faq;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.util.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFaqListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin-faq");

		Connection conn = null;

		try {

			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			conn = getConnection();

			AdminFaqDAO dao = new AdminFaqDAO(request.getServletContext());


			int listCount = dao.selectFaqCount(conn);


			int pageLimit = 10; 
			int boardLimit = 10;

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);


			List<AdminFaqDTO> list = dao.selectFaqList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());


			request.setAttribute("faqList", list);
			request.setAttribute("pageInfo", pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/faq/faqList";
	}
}
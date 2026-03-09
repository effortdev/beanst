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
			int page = 1;
			int limit = 10;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			conn = getConnection();

			AdminFaqDAO dao = new AdminFaqDAO(request.getServletContext());

			// 전체 글 수
			int listCount = dao.selectFaqCount(conn);

			// 페이지 설정
			int pageLimit = 10; // 페이지 번호 개수
			int boardLimit = 10; // 한 페이지 글 개수

			// PageInfo 생성
			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			// 목록 조회
			List<AdminFaqDTO> faqList = dao.selectFaqList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

			// request 전달
			request.setAttribute("faqList", faqList);
			request.setAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/faq/faqList";
	}
}
package com.admin.qna;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.util.PageInfo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;
		request.setAttribute("pageCss", "admin-qna");

		try {
			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminQnaDAO dao = new AdminQnaDAO(context);
			int listCount = dao.selectQnaCount(conn);

			// 페이지 설정
			int pageLimit = 10; // 페이지 번호 개수
			int boardLimit = 10; // 한 페이지 글 개수
			// PageInfo 생성
			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			List<AdminQnaDTO> list = dao.selectQnaList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

			request.setAttribute("qnaList", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageUrl", "/admin/qna/list.do");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/qna/qnaList";
	}
}
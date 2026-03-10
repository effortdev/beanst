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

			/* 현재 페이지 */
			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			/* 전체 게시글 수 (답변대기만) */
			int listCount = dao.selectWaitingCount(conn);

			/* 페이징 설정 */
			int pageLimit = 10; // 페이지 번호 개수
			int boardLimit = 10; // 한 페이지 게시글 수

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			/* 목록 조회 */
			List<AdminQnaDTO> list = dao.selectWaitingList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

			request.setAttribute("qnaList", list);
			request.setAttribute("pageInfo", pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/qna/qnaList";
	}
}
package com.admin.reservation;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.util.PageInfo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminReservationListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin-reservation");

		Connection conn = null;

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminReservationDAO dao = new AdminReservationDAO(context);

			int currentPage = 1;
			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			// 전체 글 수
			int listCount = dao.selectReservationCount(conn);
			// 페이지 설정
			int pageLimit = 10; // 페이지 번호 개수
			int boardLimit = 10; // 한 페이지 글 개수
			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			// 목록 조회
			List<AdminReservationDTO> list = dao.selectReservationList(conn, pageInfo.getStartRow(),
					pageInfo.getEndRow());
			request.setAttribute("reservationList", list);
			request.setAttribute("pageInfo", pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/reservation/list";
	}
}
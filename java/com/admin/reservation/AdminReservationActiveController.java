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

public class AdminReservationActiveController implements Action {

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

			int listCount = dao.selectActiveReservationCount(conn);

			int pageLimit = 10;
			int boardLimit = 10;

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			List<AdminReservationDTO> list = dao.selectActiveReservationList(conn, pageInfo.getStartRow(),
					pageInfo.getEndRow());

			request.setAttribute("reservationList", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageUrl", "/admin/reservation/active.do");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/reservation/list";
	}
}
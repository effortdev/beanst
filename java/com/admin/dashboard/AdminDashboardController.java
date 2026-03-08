package com.admin.dashboard;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDashboardController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminDashboardDAO dao = new AdminDashboardDAO(context);

			request.setAttribute("todayCheckIn", dao.getCount(conn, "adminTodayCheckIn"));

			request.setAttribute("todayCheckOut", dao.getCount(conn, "adminTodayCheckOut"));

			request.setAttribute("currentStay", dao.getCount(conn, "adminCurrentStay"));

			request.setAttribute("reservationRequest", dao.getCount(conn, "adminReservationRequest"));

			request.setAttribute("cancelRequest", dao.getCount(conn, "adminCancelRequest"));

			request.setAttribute("qnaWaiting", dao.getCount(conn, "adminQnaWaiting"));

			request.setAttribute("pageCss", "admin-dashboard");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/dashboard";
	}
}

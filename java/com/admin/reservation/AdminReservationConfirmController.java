package com.admin.reservation;

import static com.util.JdbcUtil.*;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminReservationConfirmController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int reservationId = Integer.parseInt(request.getParameter("reservation_id"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminReservationDAO dao = new AdminReservationDAO(context);

			int result = dao.confirmReservation(conn, reservationId);

			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
			rollback(conn);
		} finally {
			close(conn);
		}

		return "redirect:/admin/reservation/list.do";
	}
}
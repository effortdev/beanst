package com.admin.reservation;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;

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

			List<AdminReservationDTO> list = dao.selectReservationList(conn);

			request.setAttribute("reservationList", list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/reservation/list";
	}
}
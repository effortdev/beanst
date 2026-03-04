package com.admin.facility;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.commit;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			conn = getConnection();

			AdminFacilityDAO dao = new AdminFacilityDAO(request.getServletContext());

			dao.deleteFacility(conn, id);

			commit(conn);

			return "redirect:/admin/facility/list.do";

		} catch (Exception e) {

			e.printStackTrace();
			rollback(conn);

		} finally {
			close(conn);
		}

		return null;
	}
}
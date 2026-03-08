package com.admin.room;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDetailController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int roomId = Integer.parseInt(request.getParameter("room_id"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminRoomDAO dao = new AdminRoomDAO(context);

			AdminRoomDTO room = dao.selectRoomDetail(conn, roomId);

			request.setAttribute("room", room);

			request.setAttribute("pageCss", "admin-roomdetail");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/room/roomDetailEdit";
	}
}
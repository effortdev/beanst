package com.admin.room;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDetailListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminRoomDAO dao = new AdminRoomDAO(context);

			List<AdminRoomDTO> list = dao.selectRoomList(conn);

			request.setAttribute("roomList", list);

			request.setAttribute("pageCss", "admin-roomdetail");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/room/roomDetailList";
	}
}
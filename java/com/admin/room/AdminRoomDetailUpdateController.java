package com.admin.room;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.commit;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.sql.Connection;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDetailUpdateController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int roomId = Integer.parseInt(request.getParameter("room_id"));
			int baseCapacity = Integer.parseInt(request.getParameter("base_capacity"));
			int maxCapacity = Integer.parseInt(request.getParameter("max_capacity"));
			int basePrice = Integer.parseInt(request.getParameter("base_price"));
			int extraCharge = Integer.parseInt(request.getParameter("extra_charge"));

			conn = getConnection();

			ServletContext context = request.getServletContext();

			AdminRoomDAO dao = new AdminRoomDAO(context);

			AdminRoomDTO room = new AdminRoomDTO();

			room.setRoomId(roomId);
			room.setBaseCapacity(baseCapacity);
			room.setMaxCapacity(maxCapacity);
			room.setBasePrice(basePrice);
			room.setExtraCharge(extraCharge);

			int result = dao.updateRoom(conn, room);

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

		return "redirect:/admin/room/detailList.do";
	}
}
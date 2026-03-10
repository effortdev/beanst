package com.controller.admin;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.dao.AdminDAO;
import com.util.PageInfo;
import com.vo.RoomManageVO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomManageController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin_room");
		Connection conn = null;

		try {

			ServletContext context = request.getServletContext();
			AdminDAO dao = new AdminDAO(context);
			int currentPage = 1;
			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			conn = getConnection();

			int listCount = dao.selectRoomCount(conn);

			int pageLimit = 10;
			int boardLimit = 10;

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			List<RoomManageVO> roomList = dao.selectRoomList(conn, pageInfo.getStartRow(), pageInfo.getBoardLimit());

			request.setAttribute("roomList", roomList);
			request.setAttribute("pageInfo", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/room/roomManage";
	}
}
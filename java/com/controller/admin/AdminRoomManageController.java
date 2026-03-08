package com.controller.admin;

import java.util.List;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomManageVO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomManageController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin_room");

		ServletContext context = request.getServletContext();

		AdminDAO dao = new AdminDAO(context);
		List<RoomManageVO> roomList = dao.selectRoomList();

		request.setAttribute("roomList", roomList);

		return "admin/room/roomManage";
	}
}
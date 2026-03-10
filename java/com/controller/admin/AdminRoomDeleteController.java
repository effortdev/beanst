package com.controller.admin;

import java.util.List;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomImageVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {

			int room_id = Integer.parseInt(request.getParameter("room_id"));

			AdminDAO dao = new AdminDAO(request.getServletContext());

			List<RoomImageVO> imagePaths = dao.selectRoomImages(room_id);

			String uploadPath = "C:/hotelUploads/room";

			dao.deleteRoomImages(room_id);

			dao.deleteRoom(room_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/roomManage.do";
	}
}
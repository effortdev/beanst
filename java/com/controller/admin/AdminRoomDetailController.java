package com.controller.admin;

import java.util.List;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomImageVO;
import com.vo.RoomManegeVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDetailController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		int roomId = Integer.parseInt(request.getParameter("room_id"));

		AdminDAO dao = new AdminDAO(request.getServletContext());

		RoomManegeVO vo = dao.selectRoomByNo(roomId);
		List<RoomImageVO> imageVO = dao.selectRoomImages(roomId);

		request.setAttribute("vo", vo);
		request.setAttribute("imageVO", imageVO);

		return "admin/room/roomDetail";
	}
}
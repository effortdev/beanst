package com.controller;

import java.util.List;

import com.dao.RoomDAO;
import com.vo.RoomImageVO;
import com.vo.RoomManageVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoomDetailController implements Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageTitle", "Vinst Hotel");
		request.setAttribute("pageCss", "facilityDetail");

		int roomId = Integer.parseInt(request.getParameter("id"));

		RoomDAO dao = new RoomDAO(request.getServletContext());

		RoomManageVO room = dao.selectRoom(roomId);
		List<RoomImageVO> images = dao.selectRoomImages(roomId);

		request.setAttribute("room", room);
		request.setAttribute("images", images);

		return "room/roomDetail";
	}

}
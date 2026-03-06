package com.controller;

import java.util.List;

import com.dao.RoomDAO;
import com.room.RoomMainDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RoomListController implements Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "facilityList");

		RoomDAO dao = new RoomDAO(request.getServletContext());

		List<RoomMainDTO> roomList = dao.selectRoomList();

		request.setAttribute("roomList", roomList);

		return "room/roomList";
	}
}
package com.controller.admin;

import com.controller.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomAddController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageCss", "admin_room");
		return "/admin/room/roomAdd";
	}
}
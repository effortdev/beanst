package com.controller.admin;

import java.io.File;

import com.controller.Action;
import com.dao.AdminDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomImageDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		int room_id = Integer.parseInt(request.getParameter("room_id"));
		String image_path = request.getParameter("image_path");

		AdminDAO dao = new AdminDAO(request.getServletContext());
		dao.deleteRoomImage(image_path);

		String realPath = request.getServletContext().getRealPath(image_path);

		File file = new File(realPath);
		if (file.exists()) {
			file.delete();
		}
		return "redirect:/admin/roomDetail.do?room_id=" + room_id;
	}
}
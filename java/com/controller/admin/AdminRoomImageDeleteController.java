package com.controller.admin;

import java.io.File;

import com.config.UploadPath;
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

		// 파일 이름 추출
		String fileName = new File(image_path).getName();

		// 실제 업로드 폴더
		String uploadPath = UploadPath.ROOM;

		File file = new File(uploadPath, fileName);

		if (file.exists() && file.isFile()) {
			file.delete();
		}

		return "redirect:/admin/roomDetail.do?room_id=" + room_id;
	}

}

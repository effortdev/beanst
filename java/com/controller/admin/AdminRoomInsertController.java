package com.controller.admin;

import java.io.File;
import java.util.UUID;

import com.config.UploadPath;
import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomManageVO;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AdminRoomInsertController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin_room");

		try {

			String room_name = request.getParameter("room_name");
			String capacity = request.getParameter("capacity");
			String room_location = request.getParameter("room_location");
			String room_description = request.getParameter("room_description");
			String usage_time = request.getParameter("usage_time");
			String amenity = request.getParameter("amenity");
			String minibar = request.getParameter("minibar");

			RoomManageVO vo = new RoomManageVO();
			vo.setRoom_name(room_name);
			vo.setCapacity(capacity);
			vo.setRoom_location(room_location);
			vo.setRoom_description(room_description);
			vo.setUsage_time(usage_time);
			vo.setAmenity(amenity);
			vo.setMinibar(minibar);

			// 업로드 경로
			String uploadPath = UploadPath.ROOM;

			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			AdminDAO dao = new AdminDAO(request.getServletContext());

			int room_id = dao.insertRoomManage(vo);
			dao.insertRoom(room_id, vo.getRoom_name());

			int order = 1;

			for (Part part : request.getParts()) {

				if ("room_img".equals(part.getName()) && part.getSize() > 0) {

					String originalFileName = part.getSubmittedFileName();

					String ext = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

					if (!(ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png") || ext.equals(".gif")
							|| ext.equals(".webp"))) {

						throw new Exception("이미지 파일만 업로드 가능합니다.");
					}

					// UUID 파일명
					String savedFileName = UUID.randomUUID() + ext;

					File file = new File(uploadPath, savedFileName);

					part.write(file.getAbsolutePath());

					// DB 저장 경로
					String imagePath = "/upload/room/" + savedFileName;

					dao.insertRoomImage(room_id, imagePath, "N", order);

					order++;
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return "redirect:/admin/roomManage.do";
	}

}

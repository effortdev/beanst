package com.controller.admin;

import java.io.File;
import java.util.UUID;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomManegeVO;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class AdminRoomInsertController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {

			int room_id = Integer.parseInt(request.getParameter("room_id"));
			String room_name = request.getParameter("room_name");
			String capacity = request.getParameter("capacity");
			String room_location = request.getParameter("room_location");
			String room_description = request.getParameter("room_description");
			String usage_time = request.getParameter("usage_time");
			String amenity = request.getParameter("amenity");
			String minibar = request.getParameter("minibar");

			RoomManegeVO vo = new RoomManegeVO();
			vo.setRoom_id(room_id);
			vo.setRoom_name(room_name);
			vo.setCapacity(capacity);
			vo.setRoom_location(room_location);
			vo.setRoom_description(room_description);
			vo.setUsage_time(usage_time);
			vo.setAmenity(amenity);
			vo.setMinibar(minibar);

			Part filePart = request.getPart("room_img");

			String uploadPath = "C:/hotelUploads/room";

			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			AdminDAO dao = new AdminDAO(request.getServletContext());
			dao.insertRoom(vo);

			int order = 1;

			for (Part part : request.getParts()) {

				if ("room_img".equals(part.getName()) && part.getSize() > 0) {

					String originalFileName = part.getSubmittedFileName();
					String savedFileName = originalFileName;

					File targetFile = new File(uploadPath, originalFileName);

					if (targetFile.exists()) {

						String extension = "";
						int dotIndex = originalFileName.lastIndexOf(".");

						if (dotIndex != -1) {
							extension = originalFileName.substring(dotIndex);
							originalFileName = originalFileName.substring(0, dotIndex);
						}

						savedFileName = originalFileName + "_" + UUID.randomUUID() + extension;
					}

					part.write(uploadPath + File.separator + savedFileName);

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
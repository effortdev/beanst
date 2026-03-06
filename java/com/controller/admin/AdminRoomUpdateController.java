package com.controller.admin;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomManegeVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class AdminRoomUpdateController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String method = request.getMethod();
		ServletContext context = request.getServletContext();
		AdminDAO dao = new AdminDAO(context);

		int roomId = Integer.parseInt(request.getParameter("room_id"));
		String roomName = request.getParameter("room_name");
		String capacity = request.getParameter("capacity");
		String location = request.getParameter("room_location");
		String description = request.getParameter("room_description");
		String usage_time = request.getParameter("usage_time");
		String amenity = request.getParameter("amenity");
		String minibar = request.getParameter("minibar");
		if (method.equals("GET")) {

			RoomManegeVO room = dao.selectRoomByNo(roomId);

			request.setAttribute("room", room);

			return "admin/room/roomDetail";
		}

		if (method.equals("POST")) {

			try {

				System.out.println("=== 수정 실행 ===");

				RoomManegeVO vo = new RoomManegeVO();
				vo.setRoom_id(roomId);
				vo.setRoom_name(roomName);
				vo.setCapacity(capacity);
				vo.setRoom_location(location);
				vo.setRoom_description(description);
				vo.setUsage_time(usage_time);
				vo.setAmenity(amenity);
				vo.setMinibar(minibar);

				dao.updateRoom(vo);

				// 삭제할 이미지 번호 배열
				String[] deleteImages = request.getParameterValues("delete_images");

				if (deleteImages != null) {

					for (String imgId : deleteImages) {

						int imageNo = Integer.parseInt(imgId);

						// 1️⃣ DB에서 이미지 경로 가져오기
						String imagePath = dao.getImagePath(imageNo);

						// 2️⃣ 실제 파일 삭제
						if (imagePath != null) {

							String uploadPath = "C:/hotelUploads/room";

							File file = new File(uploadPath, new File(imagePath).getName());

							if (file.exists()) {
								file.delete();
								System.out.println("파일 삭제 완료 : " + file.getName());
							}
						}

						// 3️⃣ DB 삭제
						dao.deleteRoomImageDB(imageNo);
					}
				}

				Collection<Part> parts = request.getParts();

				String uploadPath = "C:/hotelUploads/room";
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

						dao.insertRoomImage(roomId, imagePath, "N", order);

						order++;
					}
				}

				String mainImage = request.getParameter("main_image");
				System.out.println("main_image = " + mainImage);

				if (mainImage != null) {
					int imageId = Integer.parseInt(mainImage);

					dao.updateMainImage(roomId, imageId);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return "redirect:/admin/roomDetail.do?room_id=" + roomId;
		}

		return null;
	}
}
package com.controller.admin;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.config.UploadPath;
import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomImageVO;
import com.vo.RoomManageVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class AdminRoomUpdateController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin_room");

		String method = request.getMethod();

		ServletContext context = request.getServletContext();
		AdminDAO dao = new AdminDAO(context);

		int roomId = Integer.parseInt(request.getParameter("room_id"));

		if (method.equals("GET")) {

			RoomManageVO vo = dao.selectRoomByNo(roomId);
			List<RoomImageVO> imageVO = dao.selectRoomImages(roomId);

			request.setAttribute("vo", vo);
			request.setAttribute("imageVO", imageVO);

			return "admin/room/roomUpdate";
		}

		if (method.equals("POST")) {

			try {

				String roomName = request.getParameter("room_name");
				String capacity = request.getParameter("capacity");
				String location = request.getParameter("room_location");
				String description = request.getParameter("room_description");
				String usage_time = request.getParameter("usage_time");
				String amenity = request.getParameter("amenity");
				String minibar = request.getParameter("minibar");

				RoomManageVO vo = new RoomManageVO();
				vo.setRoom_id(roomId);
				vo.setRoom_name(roomName);
				vo.setCapacity(capacity);
				vo.setRoom_location(location);
				vo.setRoom_description(description);
				vo.setUsage_time(usage_time);
				vo.setAmenity(amenity);
				vo.setMinibar(minibar);

				dao.updateRoom(vo);

				String uploadPath = UploadPath.ROOM;

				// -------------------------
				// 이미지 삭제
				// -------------------------

				String[] deleteImages = request.getParameterValues("delete_images");

				if (deleteImages != null) {

					for (String imgId : deleteImages) {

						int imageNo = Integer.parseInt(imgId);

						String imagePath = dao.getImagePath(imageNo);

						if (imagePath != null) {

							String fileName = new File(imagePath).getName();

							File file = new File(uploadPath, fileName);

							if (file.exists() && file.isFile()) {
								file.delete();
							}
						}

						dao.deleteRoomImageDB(imageNo);
					}
				}

				// -------------------------
				// 새 이미지 업로드
				// -------------------------

				int order = dao.getNextDisplayOrder(roomId);

				for (Part part : request.getParts()) {

					if ("room_img".equals(part.getName()) && part.getSize() > 0) {

						String originalFileName = part.getSubmittedFileName();

						String ext = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();

						if (!(ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png") || ext.equals(".gif")
								|| ext.equals(".webp"))) {

							throw new Exception("이미지 파일만 업로드 가능합니다.");
						}

						String savedFileName = UUID.randomUUID() + ext;

						File file = new File(uploadPath, savedFileName);

						part.write(file.getAbsolutePath());

						String imagePath = "/upload/room/" + savedFileName;

						dao.insertRoomImage(roomId, imagePath, "N", order);

						order++;
					}
				}

				// -------------------------
				// 대표 이미지 설정
				// -------------------------

				String mainImage = request.getParameter("main_image");

				if (mainImage != null) {

					int imageId = Integer.parseInt(mainImage);

					dao.updateMainImage(roomId, imageId);
				}

			} catch (Exception e) {

				e.printStackTrace();
			}

			return "redirect:/admin/roomUpdate.do?room_id=" + roomId;
		}

		return null;
	}

}

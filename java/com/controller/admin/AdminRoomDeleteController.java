package com.controller.admin;

import java.io.File;
import java.util.List;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomImageVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {

			int room_id = Integer.parseInt(request.getParameter("room_id"));

			AdminDAO dao = new AdminDAO(request.getServletContext());

			// 1️⃣ 해당 객실 이미지 경로 조회
			List<RoomImageVO> imagePaths = dao.selectRoomImages(room_id);

			// 2️⃣ 실제 파일 삭제
			String uploadPath = "C:/hotelUploads/room";

			for (RoomImageVO path : imagePaths) {

				// DB에는 "/upload/room/파일명" 형태로 저장되어 있음
//                String fileName = path.substring(path.lastIndexOf("/") + 1);

//                File file = new File(uploadPath, fileName);
//
//                if (file.exists()) {
//                    file.delete();
//                }
			}

			// 3️⃣ room_image 삭제
			dao.deleteRoomImages(room_id);

			// 4️⃣ room_manage 삭제
			dao.deleteRoom(room_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/roomManage.do";
	}
}
package com.controller.admin;

import java.io.File;
import java.io.PrintWriter;
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


			int reservationCount = dao.getReservationCountByRoom(room_id);

			if (reservationCount > 0) {

				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();

				out.println("<script>");
				out.println("alert('이 객실에는 예약 데이터가 있어 삭제할 수 없습니다.');");
				out.println("history.back();");
				out.println("</script>");

				return null;
			}

			List<RoomImageVO> imagePaths = dao.selectRoomImages(room_id);

			String uploadPath = "C:/hotelUploads/room";


			for (RoomImageVO img : imagePaths) {

				String fileName = new File(img.getImage_path()).getName();

				File file = new File(uploadPath, fileName);

				if (file.exists()) {
					file.delete();
				}
			}


			dao.deleteRoomImages(room_id);
			dao.deleteRoomDetail(room_id);
			dao.deleteRoom(room_id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/roomManage.do";
	}
}
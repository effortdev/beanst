package com.controller.admin;

import java.io.File;
import java.util.List;

import com.controller.Action;
import com.dao.AdminDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDeleteController implements Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        try {

            int room_no = Integer.parseInt(request.getParameter("room_no"));

            AdminDAO dao = new AdminDAO(request.getServletContext());

            // 1️⃣ 해당 객실 이미지 경로 조회
            List<String> imagePaths = dao.selectRoomImages(room_no);

            // 2️⃣ 실제 파일 삭제
            String uploadPath = "C:/hotelUploads/room";

            for (String path : imagePaths) {

                // DB에는 "/upload/room/파일명" 형태로 저장되어 있음
                String fileName = path.substring(path.lastIndexOf("/") + 1);

                File file = new File(uploadPath, fileName);

                if (file.exists()) {
                    file.delete();
                }
            }

            // 3️⃣ room_image 삭제
            dao.deleteRoomImages(room_no);

            // 4️⃣ room_manage 삭제
            dao.deleteRoom(room_no);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/admin/roomManage.do";
    }
}
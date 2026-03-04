package com.controller.admin;

import java.io.File;
import java.util.UUID;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class AdminRoomInsertController implements Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        try {

            int room_no = Integer.parseInt(request.getParameter("room_no"));
            String room_name = request.getParameter("room_name");
            String capacity = request.getParameter("capacity");
            String room_location = request.getParameter("room_location");
            String room_description = request.getParameter("room_description");

            Part filePart = request.getPart("room_img");

            String uploadPath = "C:/hotelUploads/room";

            String savedFileName = filePart.getSubmittedFileName();
            if (filePart != null && filePart.getSize() > 0) {

                String originalFileName = filePart.getSubmittedFileName();
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                File targetFile = new File(uploadPath, originalFileName);

                if (targetFile.exists()) {
                    String extension = "";
                    int dotIndex = originalFileName.lastIndexOf(".");
                    if (dotIndex != -1) {
                        extension = originalFileName.substring(dotIndex);
                        originalFileName = originalFileName.substring(0, dotIndex);
                    }

                    savedFileName = originalFileName + "_" + UUID.randomUUID() + extension;
                } else {
                    savedFileName = originalFileName;
                }

                filePart.write(uploadPath + File.separator + savedFileName);
            
            }

            RoomVO vo = new RoomVO();
            vo.setRoom_no(room_no);
            vo.setRoom_name(room_name);
            vo.setCapacity(capacity);
            vo.setRoom_location(room_location);
            vo.setRoom_description(room_description);
            vo.setRoom_image(savedFileName);

            System.out.println("savedFileName : "+savedFileName);
            System.out.println("filePart size = " + filePart.getSize());
            AdminDAO dao = new AdminDAO(request.getServletContext());
            dao.insertRoom(vo);
         // 2️⃣ room_image 저장
            if (savedFileName != null) {

                String imagePath = "/upload/room/" + savedFileName;

                dao.insertRoomImage(
                        room_no,
                        imagePath,
                        "Y",    // 대표이미지
                        1       // 순서
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/roomManage.do";
    }
}
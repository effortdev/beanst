package com.controller.admin;

import com.controller.Action;
import com.dao.AdminDAO;
import com.vo.RoomVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDetailController implements Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        int roomNo = Integer.parseInt(request.getParameter("room_no"));

        AdminDAO dao = new AdminDAO(request.getServletContext());
        RoomVO vo = dao.selectRoomByNo(roomNo);  

        request.setAttribute("vo", vo);

        return "admin/room/roomDetail";
    }
}
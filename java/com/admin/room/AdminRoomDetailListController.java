package com.admin.room;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.util.PageInfo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminRoomDetailListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();
			AdminRoomDAO dao = new AdminRoomDAO(context);

			// 현재 페이지
			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			// 전체 글 개수
			int listCount = dao.roomDetailCount(conn);

			// 페이지 설정
			int pageLimit = 10;
			int boardLimit = 10;

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			// 시작 row
			int startRow = (currentPage - 1) * boardLimit;

			// 페이징 목록 조회
			List<AdminRoomDTO> list = dao.roomDetailList(conn, startRow, boardLimit);

			request.setAttribute("roomList", list);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("pageCss", "admin-roomdetail");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "admin/room/roomDetailList";
	}
}
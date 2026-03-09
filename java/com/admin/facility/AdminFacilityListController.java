package com.admin.facility;

import static com.util.JdbcUtil.*;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.util.PageInfo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityListController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageCss", "admin-facility");
		Connection conn = null;

		try {

			conn = getConnection();

			ServletContext context = request.getServletContext();
			AdminFacilityDAO dao = new AdminFacilityDAO(context);

			int currentPage = 1;

			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}

			int listCount = dao.facilitySelectCount(conn);

			int pageLimit = 10;
			int boardLimit = 10;

			PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

			List<AdminFacilityDTO> list = dao.facilitySelectList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

			request.setAttribute("facilityList", list);
			request.setAttribute("pageInfo", pageInfo);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "/admin/facility/facility_list";
	}
}
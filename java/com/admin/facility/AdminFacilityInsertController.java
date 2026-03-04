package com.admin.facility;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.sql.Connection;
import java.util.Collection;

import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.commit;
import static com.util.JdbcUtil.rollback;
import static com.util.JdbcUtil.close;

public class AdminFacilityInsertController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("시설 등록 컨트롤러 실행됨");

		Connection conn = null;

		try {


			String facilityType = request.getParameter("facilityType");
			String facilityName = request.getParameter("facilityName");
			String location = request.getParameter("location");
			String openTime = request.getParameter("openTime");
			String description = request.getParameter("description");

			AdminFacilityDTO dto = new AdminFacilityDTO();
			dto.setFacilityType(facilityType);
			dto.setFacilityName(facilityName);
			dto.setLocation(location);
			dto.setOpenTime(openTime);
			dto.setDescription(description);

			ServletContext context = request.getServletContext();
			AdminFacilityDAO dao = new AdminFacilityDAO(context);

			conn = getConnection();


			int facilityId = dao.insertFacility(conn, dto);


			String uploadPath = context.getRealPath("/upload/facility");

			File dir = new File(uploadPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}


			String mainIndexParam = request.getParameter("mainImageIndex");

			int mainIndex = 0;

			if (mainIndexParam != null && !mainIndexParam.isEmpty()) {
				mainIndex = Integer.parseInt(mainIndexParam);
			}


			Collection<Part> parts = request.getParts();

			int index = 0;

			for (Part part : parts) {

				if (!"images".equals(part.getName()))
					continue;

				String fileName = part.getSubmittedFileName();

				if (fileName == null || fileName.isEmpty())
					continue;


				fileName = System.currentTimeMillis() + "_" + fileName;

				String savePath = uploadPath + "/" + fileName;

				part.write(savePath);

				String dbPath = "/upload/facility/" + fileName;

				String isMain = (index == mainIndex) ? "Y" : "N";

				dao.insertImage(conn, facilityId, dbPath, isMain);

				index++;
			}

			commit(conn);

			return "redirect:/admin/facility/list.do";

		} catch (Exception e) {

			e.printStackTrace();

			rollback(conn);

		} finally {

			close(conn);
		}

		return null;
	}
}
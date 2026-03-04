package com.admin.facility;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.commit;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityDeleteController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {

			int id = Integer.parseInt(request.getParameter("id"));

			conn = getConnection();

			ServletContext context = request.getServletContext();
			AdminFacilityDAO dao = new AdminFacilityDAO(context);


			String uploadPath = "C:/hotelUploads/facility";


			ArrayList<FacilityImageDTO> images = dao.selectImages(id);


			for (FacilityImageDTO img : images) {

				String imagePath = img.getImagePath();

				if (imagePath != null) {

					String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);

					File file = new File(uploadPath, fileName);

					System.out.println("삭제 대상 파일: " + file.getAbsolutePath());

					if (file.exists()) {
						file.delete();
					}
				}
			}


			dao.deleteFacility(conn, id);

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
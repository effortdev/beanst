package com.admin.facility;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItemFactory;

import java.nio.charset.StandardCharsets;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityUpdateController implements Action {

	private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;
	private static final Set<String> ALLOWED_EXT = Set.of(".jpg", ".jpeg", ".png", ".webp");

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Connection conn = null;

		try {
			ServletContext context = request.getServletContext();

			String uploadPath = "C:/hotelUploads/facility";
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdirs();
			System.out.println("파일 저장 경로: " + uploadDir.getAbsolutePath());

			if (!JakartaServletFileUpload.isMultipartContent(request)) {
				return "redirect:/admin/facility/list.do";
			}

			FileItemFactory factory = DiskFileItemFactory.builder().get();
			JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

			upload.setFileSizeMax(MAX_FILE_SIZE);

			List<FileItem> items = upload.parseRequest(request);

			int facilityId = 0;
			String facilityName = null;
			String facilityType = null;
			String location = null;
			String openTime = null;
			String description = null;
			String mainImageId = null;

			conn = getConnection();
			conn.setAutoCommit(false);

			AdminFacilityDAO dao = new AdminFacilityDAO(context);

			for (FileItem item : items) {

				if (item.isFormField()) {

					String fieldName = item.getFieldName();
					String value = item.getString(StandardCharsets.UTF_8);

					switch (fieldName) {
					case "facilityId" -> facilityId = Integer.parseInt(value);
					case "facilityName" -> facilityName = value;
					case "facilityType" -> facilityType = value;
					case "location" -> location = value;
					case "openTime" -> openTime = value;
					case "description" -> description = value;
					case "mainImageId" -> mainImageId = value;
					}
				}
			}

			dao.updateFacility(conn, facilityId, facilityName, facilityType, location, openTime, description);

			for (FileItem item : items) {

				if (item.isFormField() && "deleteImageIds".equals(item.getFieldName())) {

					int deleteId = Integer.parseInt(item.getString());

					String imagePath = dao.getImagePath(conn, deleteId);
					dao.deleteImage(conn, deleteId);

					if (imagePath != null) {
						File file = new File(context.getRealPath(imagePath));
						if (file.exists())
							file.delete();
					}
				}
			}

			for (FileItem item : items) {

				if (!item.isFormField()) {

					String fileName = item.getName();

					if (fileName == null || fileName.isBlank())
						continue;

					String ext = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

					if (!ALLOWED_EXT.contains(ext)) {
						continue;
					}

					if (item.getSize() > MAX_FILE_SIZE) {
						continue;
					}

					String newName = UUID.randomUUID() + ext;

					File file = new File(uploadDir, newName);

					item.write(Path.of(file.getAbsolutePath()));

					dao.insertImage(conn, facilityId, "/upload/facility/" + newName, "N");
				}
			}

			if (mainImageId != null && !mainImageId.isBlank()) {
				dao.resetMainImage(conn, facilityId);
				dao.setMainImage(conn, Integer.parseInt(mainImageId));
			}

			conn.commit();

		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(conn);
		}

		return "redirect:/admin/facility/list.do";
	}
}
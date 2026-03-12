package com.admin.facility;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileItemFactory;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import com.config.UploadPath;
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

			String uploadPath = UploadPath.FACILITY;

			File uploadDir = new File(uploadPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

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

			String mainImage = null;

			conn = getConnection();
			conn.setAutoCommit(false);

			AdminFacilityDAO dao = new AdminFacilityDAO(context);

			// -----------------------------
			// 폼 데이터 읽기
			// -----------------------------

			for (FileItem item : items) {

				if (!item.isFormField())
					continue;

				String field = item.getFieldName();
				String value = item.getString(StandardCharsets.UTF_8);

				switch (field) {

				case "facilityId" -> facilityId = Integer.parseInt(value);
				case "facilityName" -> facilityName = value;
				case "facilityType" -> facilityType = value;
				case "location" -> location = value;
				case "openTime" -> openTime = value;
				case "description" -> description = value;
				case "mainImage" -> mainImage = value;

				}
			}

			dao.updateFacility(conn, facilityId, facilityName, facilityType, location, openTime, description);

			// -----------------------------
			// 이미지 삭제 처리
			// -----------------------------

			for (FileItem item : items) {

				if (!(item.isFormField() && "deleteImageIds".equals(item.getFieldName())))
					continue;

				int deleteId = Integer.parseInt(item.getString(StandardCharsets.UTF_8));

				String imagePath = dao.getImagePath(conn, deleteId);

				dao.deleteImage(conn, deleteId);

				if (imagePath != null && !imagePath.isBlank()) {

					String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);

					File file = new File(uploadDir, fileName);

					if (file.exists() && file.isFile()) {
						file.delete();
					}
				}
			}

			// -----------------------------
			// 새 이미지 업로드
			// -----------------------------

			List<Integer> newImageIds = new ArrayList<>();

			for (FileItem item : items) {

				if (item.isFormField())
					continue;

				if (!"newImages".equals(item.getFieldName()))
					continue;

				String originalName = item.getName();

				if (originalName == null || originalName.isBlank())
					continue;

				originalName = new File(originalName).getName();

				int dotIndex = originalName.lastIndexOf(".");

				if (dotIndex == -1)
					continue;

				String ext = originalName.substring(dotIndex).toLowerCase();

				if (!ALLOWED_EXT.contains(ext))
					continue;

				if (item.getSize() > MAX_FILE_SIZE)
					continue;

				String newName = UUID.randomUUID() + ext;

				File file = new File(uploadDir, newName);

				item.write(file.toPath());

				String webPath = "/uploads/facility/" + newName;

				int newId = dao.insertImageReturnId(conn, facilityId, webPath, "N");

				newImageIds.add(newId);
			}

			// -----------------------------
			// 대표 이미지 설정
			// -----------------------------

			dao.resetMainImage(conn, facilityId);

			if (mainImage != null && !mainImage.isBlank()) {

				if (mainImage.startsWith("old-")) {

					int imageId = Integer.parseInt(mainImage.replace("old-", ""));

					dao.setMainImage(conn, imageId);
				}

				else if (mainImage.startsWith("new-") && !newImageIds.isEmpty()) {

					int idx = Integer.parseInt(mainImage.replace("new-", ""));

					if (idx < 0)
						idx = 0;

					if (idx >= newImageIds.size())
						idx = 0;

					int imageId = newImageIds.get(idx);

					dao.setMainImage(conn, imageId);
				}

			} else {

				dao.setFirstImageMain(conn, facilityId);
			}

			conn.commit();

			return "redirect:/admin/facility/list.do";

		} catch (Exception e) {

			rollback(conn);

			e.printStackTrace();

			return "redirect:/admin/facility/list.do";

		} finally {

			close(conn);
		}
	}
}
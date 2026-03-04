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

            String mainImageId = null;
            String mainImageIndex = null;

            conn = getConnection();
            conn.setAutoCommit(false);

            AdminFacilityDAO dao = new AdminFacilityDAO(context);

            /* =========================
               Form 데이터 파싱
            ========================= */

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
                        case "mainImageIndex" -> mainImageIndex = value;
                    }
                }
            }

            /* =========================
               시설 정보 수정
            ========================= */

            dao.updateFacility(conn, facilityId, facilityName, facilityType, location, openTime, description);

            /* =========================
               이미지 삭제
            ========================= */

            for (FileItem item : items) {

                if (item.isFormField() && "deleteImageIds".equals(item.getFieldName())) {

                    int deleteId = Integer.parseInt(item.getString());

                    String imagePath = dao.getImagePath(conn, deleteId);

                    dao.deleteImage(conn, deleteId);

                    if (imagePath != null && !imagePath.isBlank()) {

                        String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);

                        File file = new File(uploadPath + "/" + fileName);

                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
            }

            /* =========================
               새 이미지 업로드
            ========================= */

            int uploadIndex = 0;

            for (FileItem item : items) {

                if (!item.isFormField() && "newImages".equals(item.getFieldName())) {

                    String originalName = new File(item.getName()).getName();

                    if (originalName == null || originalName.isBlank()) {
                        continue;
                    }

                    String ext = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();

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

                    uploadIndex++;
                }
            }

            /* =========================
               대표 이미지 설정
            ========================= */

            if (mainImageId != null && !mainImageId.isBlank()) {

                dao.resetMainImage(conn, facilityId);
                dao.setMainImage(conn, Integer.parseInt(mainImageId));

            } else if (mainImageIndex != null && !mainImageIndex.isBlank()) {

                dao.resetMainImage(conn, facilityId);

                int index = Integer.parseInt(mainImageIndex);
                dao.setNewImageMain(conn, facilityId, index);

            } else {

                dao.resetMainImage(conn, facilityId);
                dao.setFirstImageMain(conn, facilityId);
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
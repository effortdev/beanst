package com.admin.facility;

import static com.util.JdbcUtil.close;
import static com.util.JdbcUtil.getConnection;
import static com.util.JdbcUtil.rollback;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
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
            if (!uploadDir.exists()) uploadDir.mkdirs();

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

            String mainImageId = null;      // 기존 이미지 대표 선택
            String mainImageIndex = null;   // 새 이미지 대표 선택(파일 index)

            conn = getConnection();
            conn.setAutoCommit(false);

            AdminFacilityDAO dao = new AdminFacilityDAO(context);

            // 1) 폼 파싱
            for (FileItem item : items) {
                if (!item.isFormField()) continue;

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

            // ✅ 디버깅 로그(문제 잡을 때 필수)
            System.out.println("[UPDATE] facilityId=" + facilityId);
            System.out.println("[UPDATE] mainImageId=" + mainImageId);
            System.out.println("[UPDATE] mainImageIndex=" + mainImageIndex);

            // 2) 시설 수정
            dao.updateFacility(conn, facilityId, facilityName, facilityType, location, openTime, description);

            // 3) 이미지 삭제
            for (FileItem item : items) {
                if (!(item.isFormField() && "deleteImageIds".equals(item.getFieldName()))) continue;

                int deleteId = Integer.parseInt(item.getString(StandardCharsets.UTF_8));
                String imagePath = dao.getImagePath(conn, deleteId);
                dao.deleteImage(conn, deleteId);

                if (imagePath != null && !imagePath.isBlank()) {
                    String fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1);
                    File file = new File(uploadPath + "/" + fileName);
                    if (file.exists()) file.delete();
                }
            }

            // 4) 새 이미지 업로드 (업로드된 image_id를 저장)
            List<Integer> newImageIds = new ArrayList<>();
            for (FileItem item : items) {
                if (item.isFormField()) continue;
                if (!"newImages".equals(item.getFieldName())) continue;

                String originalName = new File(item.getName()).getName();
                if (originalName == null || originalName.isBlank()) continue;

                String ext = originalName.substring(originalName.lastIndexOf(".")).toLowerCase();
                if (!ALLOWED_EXT.contains(ext)) continue;

                if (item.getSize() > MAX_FILE_SIZE) continue;

                String newName = UUID.randomUUID() + ext;
                File file = new File(uploadDir, newName);
                item.write(Path.of(file.getAbsolutePath()));

                // 일단 N으로 넣고, 나중에 정확한 image_id로 대표 세팅
                int newId = dao.insertImageReturnId(conn, facilityId, "/upload/facility/" + newName, "N");
                newImageIds.add(newId);
            }

            // 5) 대표 이미지 설정 (여기가 핵심)
            dao.resetMainImage(conn, facilityId);

            if (mainImageId != null && !mainImageId.isBlank()) {
                // 기존 이미지 대표
                dao.setMainImage(conn, Integer.parseInt(mainImageId));

            } else if (mainImageIndex != null && !mainImageIndex.isBlank() && !newImageIds.isEmpty()) {
                // 새 이미지 대표: "선택 index" -> "방금 insert된 image_id"로 매핑
                int idx = Integer.parseInt(mainImageIndex);
                if (idx < 0) idx = 0;
                if (idx >= newImageIds.size()) idx = 0;

                int targetImageId = newImageIds.get(idx);
                dao.setMainImage(conn, targetImageId);

            } else {
                // 아무 선택도 없으면: 최신/첫번째 정책에 맞춰 setFirstImage (ORDER BY 꼭!)
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
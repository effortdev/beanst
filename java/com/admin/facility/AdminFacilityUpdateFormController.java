package com.admin.facility;

import java.util.ArrayList;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityUpdateFormController implements Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            ServletContext context = request.getServletContext();
            AdminFacilityDAO dao = new AdminFacilityDAO(context);

            AdminFacilityDTO facility = dao.selectOne(id);
            ArrayList<FacilityImageDTO> imageList = dao.selectImages(id);

            request.setAttribute("facility", facility);
            request.setAttribute("imageList", imageList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/facility/facility_update";
    }
}
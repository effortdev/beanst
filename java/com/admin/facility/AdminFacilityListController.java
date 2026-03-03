package com.admin.facility;

import java.util.ArrayList;

import com.controller.Action;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityListController implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {

            ServletContext context = request.getServletContext();


            AdminFacilityDAO dao = new AdminFacilityDAO(context);


            ArrayList<AdminFacilityDTO> list = dao.selectAll();


            request.setAttribute("facilityList", list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "/admin/facility/facility_list";
    }
}
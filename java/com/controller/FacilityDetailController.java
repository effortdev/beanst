package com.controller;

import com.facility.FacilityDTO;
import com.facility.FacilityImageDTO;
import com.facility.FacilityDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public class FacilityDetailController implements Action {

    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
    	
    	
		request.setAttribute("pageTitle", "Vinst Hotel");
		request.setAttribute("pageCss", "facilityDetail");

        int facilityId = Integer.parseInt(request.getParameter("id"));

        FacilityDAO dao = new FacilityDAO(request.getServletContext());

        FacilityDTO facility = dao.selectFacility(facilityId);
        List<FacilityImageDTO> images = dao.selectFacilityImages(facilityId);

        request.setAttribute("facility", facility);
        request.setAttribute("images", images);

        return "facility/detail";
    }
}
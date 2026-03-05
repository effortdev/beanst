package com.controller;

import java.util.List;

import com.facility.FacilityDAO;
import com.facility.FacilityMainDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FacilityListController implements Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("pageCss", "facilityList");

		FacilityDAO dao = new FacilityDAO(request.getServletContext());

		List<FacilityMainDTO> list = dao.selectFacilityList();

		request.setAttribute("facilityList", list);

		return "facility/list";
	}
}
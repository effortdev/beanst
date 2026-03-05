package com.controller;

import java.util.List;

import com.facility.FacilityMainDTO;
import com.home.HomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute("pageTitle", "Vinst Hotel");
		request.setAttribute("pageCss", "home_main");

		HomeService service = new HomeService();

		List<FacilityMainDTO> facilityList = service.getFacilityList(request.getServletContext());

		request.setAttribute("facilityList", facilityList);

		return "home/main";
	}
}
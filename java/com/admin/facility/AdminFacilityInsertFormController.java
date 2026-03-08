package com.admin.facility;

import com.controller.Action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminFacilityInsertFormController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageCss", "admin-facility");
		return "admin/facility/facility_insertForm";

	}
}
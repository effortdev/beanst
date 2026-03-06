package com.home;

import java.util.List;

import com.facility.FacilityDAO;
import com.facility.FacilityMainDTO;

import jakarta.servlet.ServletContext;

public class HomeService {

	public List<FacilityMainDTO> getFacilityList(ServletContext context) {

		FacilityDAO dao = new FacilityDAO(context);
		

		return dao.selectMainFacility();
	}
}
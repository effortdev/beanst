package com.home;

import java.util.List;

import com.dao.RoomDAO;
import com.facility.FacilityDAO;
import com.facility.FacilityMainDTO;
import com.room.RoomMainDTO;

import jakarta.servlet.ServletContext;

public class HomeService {

	public List<FacilityMainDTO> getFacilityList(ServletContext context) {

		FacilityDAO dao = new FacilityDAO(context);

		return dao.selectMainFacility();
	}

	// 객실목록
	public List<RoomMainDTO> getRoomList(ServletContext context) {

		RoomDAO dao = new RoomDAO(context);

		return dao.selectMainRoom();
	}

}
package com.handler;

import java.util.HashMap;

import com.admin.facility.AdminFacilityListController;
import com.admin.facility.AdminFacilityUpdateController;
import com.admin.facility.AdminFacilityUpdateFormController;
import com.controller.Action;
import com.controller.HomeController;
import com.controller.LoginController;
import com.controller.LogoutController;
import com.controller.MemberMypageController;
import com.controller.ReservationLocationController;
import com.controller.ReservationMainController;
import com.controller.admin.AdminDashboardController;
import com.controller.admin.AdminLoginController;
import com.controller.admin.AdminLogoutController;

public class HandlerMapper {

	private HashMap<String, Action> map = new HashMap<>();

	public HandlerMapper() {
		map.put("/main.do", new HomeController()); // 메인
		map.put("/member/mypage.do", new MemberMypageController());
		map.put("/reservationMain.do", new ReservationMainController());
		map.put("/login/login.do", new LoginController());
		map.put("/logout.do", new LogoutController());
		map.put("/reservationLocation.do", new ReservationLocationController());
		
		
		
		
		
		map.put("/admin/login.do", new AdminLoginController());
		map.put("/admin/dashboard.do", new AdminDashboardController());
		map.put("/admin/logout.do", new AdminLogoutController());
		map.put("/admin/facility/list.do", new AdminFacilityListController());
		map.put("/admin/facility/updateForm.do", new AdminFacilityUpdateFormController());
		map.put("/admin/facility/update.do" , new AdminFacilityUpdateController());
	}

	public Action getController(String path) {
		return map.get(path);
	}
}





package com.handler;

import java.util.HashMap;

import com.admin.facility.AdminFacilityDeleteController;
import com.admin.facility.AdminFacilityInsertController;
import com.admin.facility.AdminFacilityInsertFormController;
import com.admin.facility.AdminFacilityListController;
import com.admin.facility.AdminFacilityUpdateController;
import com.admin.facility.AdminFacilityUpdateFormController;
import com.admin.qna.AdminQnaAnswerController;
import com.admin.qna.AdminQnaDeleteController;
import com.admin.qna.AdminQnaDetailController;
import com.admin.qna.AdminQnaListController;
import com.admin.qna.AdminQnaWaitingController;
import com.controller.Action;
import com.controller.FacilityDetailController;
import com.controller.FacilityListController;
import com.controller.HomeController;
import com.controller.JoinCompleteController;
import com.controller.JoinController;
import com.controller.LoginController;
import com.controller.LogoutController;
import com.controller.MemberMypageController;
import com.controller.ReservationLocationController;
import com.controller.ReservationMainController;
import com.controller.admin.AdminDashboardController;
import com.controller.admin.AdminLoginController;
import com.controller.admin.AdminLogoutController;
import com.controller.admin.AdminRoomAddController;
import com.controller.admin.AdminRoomDeleteController;
import com.controller.admin.AdminRoomDetailController;
import com.controller.admin.AdminRoomImageDeleteController;
import com.controller.admin.AdminRoomInsertController;
import com.controller.admin.AdminRoomManageController;
import com.controller.admin.AdminRoomUpdateController;
import com.controller.board.FaqListController;
import com.controller.board.QnaDetailController;
import com.controller.board.QnaInsertController;
import com.controller.board.QnaListController;
import com.controller.board.QnaWriteController;
import com.controller.reservation.BookingController;
import com.controller.reservation.ReservationCheckController;
import com.controller.reservation.ReservationCompleteController;

public class HandlerMapper {

	private HashMap<String, Action> map = new HashMap<>();

	public HandlerMapper() {
		map.put("/main.do", new HomeController()); // 메인
		map.put("/member/mypage.do", new MemberMypageController());

		map.put("/login/login.do", new LoginController()); // 로그인
		map.put("/logout.do", new LogoutController());
		map.put("/member/join.do", new JoinController());
		map.put("/member/joinComplete.do", new JoinCompleteController());

		map.put("/faqList.do", new FaqListController()); // 게시판
		map.put("/qnaList.do", new QnaListController());
		map.put("/qnaDetail.do", new QnaDetailController());
		map.put("/qnaWriteForm.do", new QnaWriteController());
		map.put("/qnaInsert.do", new QnaInsertController());

		map.put("/facilityDetail.do", new FacilityDetailController()); // 호텔정보 관련
		map.put("/facilityList.do", new FacilityListController());

		map.put("/reservationLocation.do", new ReservationLocationController()); // 예약관련
		map.put("/reservationMain.do", new ReservationMainController());
		map.put("/reservation/booking.do", new BookingController());
		map.put("/reservation/check.do", new ReservationCheckController());
		map.put("/reservation/complete.do", new ReservationCompleteController());

		map.put("/admin/login.do", new AdminLoginController()); // 관리자
		map.put("/admin/dashboard.do", new AdminDashboardController());
		map.put("/admin/logout.do", new AdminLogoutController());
		map.put("/admin/facility/list.do", new AdminFacilityListController());
		map.put("/admin/facility/insertForm.do", new AdminFacilityInsertFormController());
		map.put("/admin/facility/insert.do", new AdminFacilityInsertController());
		map.put("/admin/facility/updateForm.do", new AdminFacilityUpdateFormController());
		map.put("/admin/facility/update.do", new AdminFacilityUpdateController());
		map.put("/admin/facility/delete.do", new AdminFacilityDeleteController());
		map.put("/admin/roomDetail.do", new AdminRoomDetailController());
		map.put("/admin/deleteRoomImage.do", new AdminRoomImageDeleteController());
		map.put("/admin/roomUpdate.do", new AdminRoomUpdateController());
		map.put("/admin/roomDelete.do", new AdminRoomDeleteController());
		map.put("/admin/roomInsert.do", new AdminRoomInsertController());
		map.put("/admin/roomAdd.do", new AdminRoomAddController());
		map.put("/admin/roomAdd.do", new AdminRoomAddController());
		map.put("/admin/roomManage.do", new AdminRoomManageController());
		map.put("/admin/qna/list.do", new AdminQnaListController());
		map.put("/admin/qna/detail.do", new AdminQnaDetailController());
		map.put("/admin/qna/answer.do", new AdminQnaAnswerController());
		map.put("/admin/qna/delete.do", new AdminQnaDeleteController());
		map.put("/admin/qna/waiting.do", new AdminQnaWaitingController());

	}

	public Action getController(String path) {
		return map.get(path);
	}
}

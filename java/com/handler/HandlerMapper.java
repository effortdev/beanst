package com.handler;

import java.util.HashMap;

import com.admin.dashboard.AdminDashboardController;
import com.admin.facility.AdminFacilityDeleteController;
import com.admin.facility.AdminFacilityInsertController;
import com.admin.facility.AdminFacilityInsertFormController;
import com.admin.facility.AdminFacilityListController;
import com.admin.facility.AdminFacilityUpdateController;
import com.admin.facility.AdminFacilityUpdateFormController;
import com.admin.faq.AdminFaqDeleteController;
import com.admin.faq.AdminFaqEditController;
import com.admin.faq.AdminFaqInsertController;
import com.admin.faq.AdminFaqListController;
import com.admin.faq.AdminFaqUpdateController;
import com.admin.faq.AdminFaqWriteController;
import com.admin.qna.AdminQnaAnswerController;
import com.admin.qna.AdminQnaDeleteController;
import com.admin.qna.AdminQnaDetailController;
import com.admin.qna.AdminQnaListController;
import com.admin.qna.AdminQnaWaitingController;
import com.admin.reservation.AdminReservationActiveController;
import com.admin.reservation.AdminReservationCancelController;
import com.admin.reservation.AdminReservationConfirmController;
import com.admin.reservation.AdminReservationListController;
import com.admin.room.AdminRoomDetailController;
import com.admin.room.AdminRoomDetailListController;
import com.admin.room.AdminRoomDetailUpdateController;
import com.controller.Action;
import com.controller.FacilityDetailController;
import com.controller.FacilityListController;
import com.controller.FindIdController;
import com.controller.FindPwController;
import com.controller.HomeController;
import com.controller.JoinCompleteController;
import com.controller.JoinController;
import com.controller.LoginController;
import com.controller.LogoutController;
import com.controller.MemberMypageController;
import com.controller.MyPageController;
import com.controller.ReservationCancelController;
import com.controller.ReservationCheckController;
import com.controller.ReservationLocationController;
import com.controller.ReservationMainController;
import com.controller.ResetCompleteController;
import com.controller.ResetPwController;
import com.controller.RoomDetailController;
import com.controller.RoomListController;
import com.controller.UpdateController;
import com.controller.admin.AdminLoginController;
import com.controller.admin.AdminLogoutController;
import com.controller.admin.AdminRoomAddController;
import com.controller.admin.AdminRoomDeleteController;
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
		map.put("/member/myPage.do", new MyPageController());
		map.put("/member/update.do", new UpdateController());

		map.put("/member/findId.do", new FindIdController());
		map.put("/member/findPw.do", new FindPwController());
		map.put("/member/resetPw.do", new ResetPwController());
		map.put("/member/resetComplete.do", new ResetCompleteController());

		map.put("/faqList.do", new FaqListController()); // 게시판
		map.put("/qnaList.do", new QnaListController());
		map.put("/qnaDetail.do", new QnaDetailController());
		map.put("/qnaWriteForm.do", new QnaWriteController());
		map.put("/qnaInsert.do", new QnaInsertController());

		map.put("/facilityDetail.do", new FacilityDetailController()); // 호텔정보 관련
		map.put("/facilityList.do", new FacilityListController());
		map.put("/roomDetail.do", new RoomDetailController());// 객실정보 상세보기
		map.put("/roomList.do", new RoomListController());// 객실 리스트 보기

		map.put("/reservationLocation.do", new ReservationLocationController()); // 예약관련
		map.put("/reservationMain.do", new ReservationMainController());
		map.put("/reservation/booking.do", new BookingController());
		map.put("/reservation/check.do", new ReservationCheckController());
		map.put("/reservation/complete.do", new ReservationCompleteController());
		map.put("/reservation/cancel.do", new ReservationCancelController());

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

		map.put("/admin/room/detailList.do", new AdminRoomDetailListController());
		map.put("/admin/room/edit.do", new AdminRoomDetailController());
		map.put("/admin/room/update.do", new AdminRoomDetailUpdateController());

		map.put("/admin/qna/list.do", new AdminQnaListController());
		map.put("/admin/qna/detail.do", new AdminQnaDetailController());
		map.put("/admin/qna/answer.do", new AdminQnaAnswerController());
		map.put("/admin/qna/delete.do", new AdminQnaDeleteController());
		map.put("/admin/qna/waiting.do", new AdminQnaWaitingController());
		map.put("/admin/faq/list.do", new AdminFaqListController());
		map.put("/admin/faq/write.do", new AdminFaqWriteController());
		map.put("/admin/faq/insert.do", new AdminFaqInsertController());
		map.put("/admin/faq/edit.do", new AdminFaqEditController());
		map.put("/admin/faq/update.do", new AdminFaqUpdateController());
		map.put("/admin/faq/delete.do", new AdminFaqDeleteController());
		map.put("/admin/reservation/list.do", new AdminReservationListController());
		map.put("/admin/reservation/confirm.do", new AdminReservationConfirmController());
		map.put("/admin/reservation/cancel.do", new AdminReservationCancelController());
		map.put("/admin/reservation/active.do", new AdminReservationActiveController());

	}

	public Action getController(String path) {
		return map.get(path);
	}
}

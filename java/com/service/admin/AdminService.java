package com.service.admin;

import java.util.List;
import jakarta.servlet.ServletContext;

import com.dao.AdminDAO;
import com.vo.UserVO;

public class AdminService {

	private ServletContext context;

	// 생성자에서 context를 받아 AdminDAO로 넘겨줍니다.
	public AdminService(ServletContext context) {
		this.context = context;
	}

	// 1. 전체 회원 목록 조회
	public List<UserVO> getAllUsers() {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserList();
	}

	// 2. 특정 회원 상세 정보 조회
	public UserVO getUserDetail(String userId) {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserDetail(userId);
	}

	// 3. 회원 정보 업데이트
	public boolean updateUser(UserVO user) {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserUpdate(user);
	}

	// 4. 탈퇴 요청 승인
	public boolean approveWithdraw(String userId) {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserWithdrawApprove(userId);
	}
}
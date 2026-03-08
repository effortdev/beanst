package com.service;

import com.dao.UserDAO;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class UserService {
	private ServletContext context;

	public UserService(ServletContext context) {
		this.context = context;
	}

	// 1️⃣ 회원가입
	public boolean joinUser(UserVO userVO) {
		UserDAO dao = new UserDAO(context);
		return dao.insertUser(userVO);
	}

	// 2️⃣ 아이디 찾기
	public String findUserId(String name, String email) {
		UserDAO dao = new UserDAO(context);
		return dao.findId(name, email);
	}

	// 3️⃣ 회원 정보 확인 (비밀번호 찾기 전용)
	public boolean isUserValid(String userId, String name, String email) {
		return new UserDAO(context).checkUserForPw(userId, name, email);
	}

	// 4️⃣ 비밀번호 변경
	public boolean resetPassword(String userId, String newPw) {
		return new UserDAO(context).updatePassword(userId, newPw);
	}

	// 5️⃣ 현재 비밀번호 확인
	public boolean checkPassword(String userId, String password) {
		return new UserDAO(context).checkPassword(userId, password);
	}

	// 6️⃣ 연락처 수정
	public boolean updateContact(String userId, String email, String phone) {
		return new UserDAO(context).updateContact(userId, email, phone);
	}

	// 7️⃣ 회원 상태 변경 (회원탈퇴 포함)
	public boolean updateMemberStatus(UserVO user) {
		UserDAO dao = new UserDAO(context);
		return dao.updateStatus(user.getUserId(), user.getStatus());
	}
}
package com.service;

import com.dao.UserDAO;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class UserService {
	private UserDAO dao;

	public UserService(ServletContext context) {
		this.dao = new UserDAO(context);
	}

	// 1️⃣ 회원가입
	public boolean joinUser(UserVO userVO) {
		return dao.insertUser(userVO);
	}

	// 2️⃣ 아이디 찾기
	public String findUserId(String name, String email) {
		return dao.findId(name, email);
	}

	// 3️⃣ 회원 정보 확인 (비밀번호 찾기 전용)
	public boolean isUserValid(String userId, String name, String email) {
		return dao.checkUserForPw(userId, name, email);
	}

	// 4️⃣ 비밀번호 변경
	public boolean resetPassword(String userId, String newPw) {
		return dao.updatePassword(userId, newPw);
	}

	// 5️⃣ 현재 비밀번호 확인
	public boolean checkPassword(String userId, String password) {
		return dao.checkPassword(userId, password);
	}

	// 6️⃣ 연락처 수정
	public boolean updateContact(String userId, String email, String phone) {
		return dao.updateContact(userId, email, phone);
	}

	// 7️⃣ 회원 상태 변경 (회원탈퇴 포함)
	public boolean updateMemberStatus(UserVO user) {

		return dao.updateStatus(user.getUserId(), user.getStatus());
	}

	public UserVO selectUser(String id) {
		return dao.selectUser(id);
	}

	// 9 이메일 중복검사
	public boolean isEmailDuplicate(String email, String userId) {
		return dao.isEmailDuplicate(email, userId);
	}
}
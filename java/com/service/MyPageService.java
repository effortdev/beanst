package com.service;

import java.sql.Connection;

import com.dao.MyPageDAO;
import com.util.JdbcUtil;
import com.vo.UserVO;

import jakarta.servlet.ServletContext;

public class MyPageService {
	private MyPageDAO dao;

	public MyPageService(ServletContext context) {
		dao = new MyPageDAO(context);
	}

	public UserVO getMyInfo(String userId) {
		Connection con = JdbcUtil.getConnection();
		UserVO vo = dao.selectMyInfo(con, userId);
		JdbcUtil.close(con);
		return vo;
	}

	public boolean checkPassword(String userId, String password) {
		Connection con = JdbcUtil.getConnection();
		boolean result = dao.checkPassword(con, userId, password);
		JdbcUtil.close(con);
		return result;
	}

	public boolean isEmailDup(String email, String userId) {
		Connection con = JdbcUtil.getConnection();
		boolean result = dao.checkDuplicate(con, "checkEmailDup", email, userId);
		JdbcUtil.close(con);
		return result;
	}

	public boolean isPhoneDup(String phone, String userId) {
		Connection con = JdbcUtil.getConnection();
		boolean result = dao.checkDuplicate(con, "checkPhoneDup", phone, userId);
		JdbcUtil.close(con);
		return result;
	}

	public boolean updateMyInfo(UserVO vo) {
		Connection con = JdbcUtil.getConnection();
		boolean result = dao.updateMyInfo(con, vo) > 0;
		if (result) JdbcUtil.commit(con); else JdbcUtil.rollback(con);
		JdbcUtil.close(con);
		return result;
	}

	public boolean updateMyPassword(String userId, String newPw) {
		Connection con = JdbcUtil.getConnection();
		boolean result = dao.updateSingleField(con, "updateMyPassword", newPw, userId) > 0;
		if (result) JdbcUtil.commit(con); else JdbcUtil.rollback(con);
		JdbcUtil.close(con);
		return result;
	}

	public boolean withdrawUser(String userId) {
		Connection con = JdbcUtil.getConnection();
		boolean result = dao.updateSingleField(con, "withdrawMyStatus", "2", userId) > 0;
		if (result) JdbcUtil.commit(con); else JdbcUtil.rollback(con);
		JdbcUtil.close(con);
		return result;
	}
}
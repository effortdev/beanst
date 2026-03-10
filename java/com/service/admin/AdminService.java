package com.service.admin;

import java.sql.Connection;
import java.util.List;
import jakarta.servlet.ServletContext;

import com.dao.AdminDAO;
import com.vo.UserVO;

public class AdminService {

	private ServletContext context;

	public AdminService(ServletContext context) {
		this.context = context;
	}

	public List<UserVO> getAllUsers() {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserList();
	}

	public UserVO getUserDetail(String userId) {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserDetail(userId);
	}

	public boolean updateUser(UserVO user) {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserUpdate(user);
	}

	public boolean approveWithdraw(String userId) {
		AdminDAO dao = new AdminDAO(context);
		return dao.adminUserWithdrawApprove(userId);
	}

	public int selectUserCount(Connection conn) {
		AdminDAO dao = new AdminDAO(context);
		return dao.selectUserCount(conn);
	}

	public List<UserVO> selectUserList(Connection conn, int startRow, int endRow) {
		AdminDAO dao = new AdminDAO(context);
		return dao.selectUserList(conn, startRow, endRow);
	}
}
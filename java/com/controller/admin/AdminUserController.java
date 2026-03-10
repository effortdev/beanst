package com.controller.admin;

import static com.util.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.List;

import com.controller.Action;
import com.service.admin.AdminService; // (본인 프로젝트의 서비스 클래스명에 맞게 수정하세요)
import com.util.PageInfo;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminUserController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("pageCss", "admin-member");
		Connection conn = null;
		String method = request.getMethod();
		String action = request.getParameter("action");
		AdminService service = new AdminService(request.getServletContext());

		int currentPage = 1;

		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		conn = getConnection();

		int listCount = service.selectUserCount(conn);

		int pageLimit = 10;
		int boardLimit = 10;

		int totalPage = (int) Math.ceil((double) listCount / boardLimit);

		PageInfo pageInfo = new PageInfo(currentPage, listCount, pageLimit, boardLimit);

		List<UserVO> list = service.selectUserList(conn, pageInfo.getStartRow(), pageInfo.getEndRow());

		request.setAttribute("userList", list);
		request.setAttribute("pageInfo", pageInfo);

		if (method.equals("GET")) {

			if ("edit".equals(action)) {
				String userId = request.getParameter("userId");
				UserVO user = service.getUserDetail(userId);
				request.setAttribute("user", user);
				return "admin/member/adminMemberEdit";
			}

			String filter = request.getParameter("filter");

			if ("withdraw".equals(filter)) {
				List<UserVO> allUsers = service.getAllUsers();
				List<UserVO> filteredList = new java.util.ArrayList<>();

				if (allUsers != null) {
					for (UserVO user : allUsers) {
						if ("2".equals(user.getStatus())) {
							filteredList.add(user);
						}
					}
				}
				request.setAttribute("userList", filteredList);

				request.setAttribute("pageInfo",
						new PageInfo(1, filteredList.size(), 10, filteredList.size() > 0 ? filteredList.size() : 1));

			} else {

				request.setAttribute("userList", list);
			}
			return "admin/member/adminMemberList";
		}

		if (method.equals("POST")) {

			if ("approveWithdraw".equals(action)) {
				String userId = request.getParameter("userId");
				boolean result = service.approveWithdraw(userId);
				if (result) {
					request.setAttribute("msg", "탈퇴 처리가 완료되었습니다.");
				}
				return "redirect:/admin/memberManage.do?msg=withdraw";
			}

			if ("update".equals(action)) {
				UserVO vo = new UserVO();
				vo.setUserId(request.getParameter("user_id"));
				vo.setName(request.getParameter("name"));
				vo.setEmail(request.getParameter("email"));
				vo.setPhone(request.getParameter("phone"));
				vo.setRole(request.getParameter("role"));
				vo.setStatus(request.getParameter("status"));

				boolean result = service.updateUser(vo);
				return "redirect:/admin/memberManage.do?msg=update";
			}
		}

		return null;
	}
}
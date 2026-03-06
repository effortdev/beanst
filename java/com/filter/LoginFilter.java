package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import com.vo.UserVO;

@WebFilter(urlPatterns = { "/reservation/*" , "/reservationMain.do" , "/qnaList.do" })
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		// 세션에 'login' 정보가 있는지 확인
//        boolean isLogin = (session != null && session.getAttribute("loginMember") != null);
		// 세션에서 loginMember 객체 가져오기
		Object loginObj = (session != null) ? session.getAttribute("loginMember") : null;
		boolean isLogin = (loginObj != null);

		if (isLogin) {

			UserVO loginMember = (UserVO) loginObj;
			System.out.println("현재 로그인한 사람의 Role 값: " + loginMember.getRole());

			// 권한이 ADMIN인지 확인
			if ("admin".equalsIgnoreCase(loginMember.getRole())) {

				if (session != null) {
					session.invalidate();
				}
				// ⭐️ 응답 방식을 HTML(UTF-8)로 설정
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();

				// ⭐️ 자바스크립트로 알림창을 띄우고 원하는 페이지로 이동시킴
				out.println("<script>");
				out.println("alert('관리자 페이지에서 로그인해주세요.');");
				// 관리자 로그인 페이지 경로가 따로 있다면 아래 주소를 수정해주세요. (예: /admin/login.do)
				out.println("location.href='" + req.getContextPath() + "/main.do';");
				out.println("</script>");

				out.flush();
				return; // 여기서 흐름을 완전히 종료합니다.
			}

			// 로그인 상태라면 요청한 페이지로 이동
			chain.doFilter(request, response);
		} else {
			// System.out.println(req.getContextPath());
			// 로그인 상태가 아니라면 로그인 페이지로 리다이렉트
			// res.sendRedirect(req.getContextPath() + "/login/login.do");
			String uri = req.getRequestURI();
			String contextPath = req.getContextPath();
			String path = uri.substring(contextPath.length());

			// 이 주소로 이동해야 LoginController가 CSS를 세팅해줍니다.
			res.sendRedirect(contextPath + "/login/login.do?dest=" + path);
		}
	}
}
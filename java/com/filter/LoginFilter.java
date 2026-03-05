package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = { "/reservation/booking.do", "/reservation/check.do", "/qnaList.do" })
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		// 세션에 'login' 정보가 있는지 확인
		boolean isLogin = (session != null && session.getAttribute("loginMember") != null);

		if (isLogin) {
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
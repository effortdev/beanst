package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import com.vo.UserVO;

@WebFilter(urlPatterns = { "/reservation/*", "/reservationMain.do", "/qnaList.do" })
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		Object loginObj = (session != null) ? session.getAttribute("loginMember") : null;
		boolean isLogin = (loginObj != null);

		if (isLogin) {

			UserVO loginMember = (UserVO) loginObj;

			if ("admin".equalsIgnoreCase(loginMember.getRole())) {

				if (session != null) {
					session.invalidate();
				}
				res.setContentType("text/html; charset=UTF-8");
				PrintWriter out = res.getWriter();

				out.println("<script>");
				out.println("alert('관리자 페이지에서 로그인해주세요.');");
				out.println("location.href='" + req.getContextPath() + "/main.do';");
				out.println("</script>");

				out.flush();
				return;
			}

			chain.doFilter(request, response);
		} else {

			String uri = req.getRequestURI();
			String contextPath = req.getContextPath();
			String path = uri.substring(contextPath.length());

			res.sendRedirect(contextPath + "/login/login.do?dest=" + path);
		}
	}
}
package com.filter;

import java.io.IOException;

import com.dto.AdminDTO;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/admin/*")
public class AdminAuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();


		if (uri.contains("/admin/login.do")) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("admin") == null) {
			res.sendRedirect(req.getContextPath() + "/admin/login.do");
			return;
		}

		AdminDTO admin = (AdminDTO) session.getAttribute("admin");

		if (!"admin".equals(admin.getRole())) {
			res.setContentType("text/html; charset=UTF-8"); 
			java.io.PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('관리자 권한이 없습니다. 메인 페이지로 이동합니다.');");

			out.println("location.href='" + req.getContextPath() + "/';");
			out.println("</script>");
			out.flush();
			return;
		}

		chain.doFilter(request, response);
	}
}
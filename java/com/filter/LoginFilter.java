package com.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String contextPath = req.getContextPath();
        String uri = req.getRequestURI();
        String path = uri.substring(contextPath.length());

        HttpSession session = req.getSession(false);

        boolean isLoggedIn =
                (session != null &&
                 session.getAttribute("loginMember") != null);

        boolean isLoginPage =
                path.equals("/member/login");

        boolean isPublic =
                path.equals("/") ||
                path.startsWith("/resources/");

        boolean needsLogin =
                path.startsWith("/member/mypage") ||
                path.startsWith("/reservations/new");

        if (needsLogin && !isLoggedIn) {
            res.sendRedirect(contextPath + "/member/login");
            return;
        }

        chain.doFilter(request, response);
    }
}
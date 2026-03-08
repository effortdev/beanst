package com.controller;

import com.service.LoginService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController implements Action  {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String method = request.getMethod();
        request.setAttribute("pageCss", "login");

    
        if (method.equals("GET")) {
            return "login/login";
        }


        if (method.equals("POST")) {

            String userId = request.getParameter("user_id");
            String password = request.getParameter("password");

            
            LoginService service =
                    new LoginService(request.getServletContext());

            UserVO login = service.login(userId, password);

            if (login != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loginMember", login);
                
             // 2. 로그인 성공 시 원래 가려던 곳으로 보냄
                String dest = request.getParameter("dest");
                if (dest != null && !dest.isEmpty()) {
                    return "redirect:" + dest; 
                }

                return "redirect:/reservationMain.do";
            } else {
            	// 로그인 실패 시 dest를 유지하기 위해 다시 전달
                String dest = request.getParameter("dest");
                request.setAttribute("dest", dest);
                request.setAttribute("errorMsg", "아이디 또는 비밀번호가 틀렸습니다.");
                return "login/login";
            }
        }

        return null;
    }
}
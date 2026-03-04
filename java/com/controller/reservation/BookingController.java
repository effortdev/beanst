package com.controller.reservation;

import com.controller.Action;
import com.service.LoginService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class BookingController implements Action {

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String method = request.getMethod();

    
//        if (method.equals("GET")) {
//            return "login/login";
//        }


//        if (method.equals("POST")) {
//
//            String userId = request.getParameter("user_id");
//            String password = request.getParameter("password");
//
//            
//            LoginService service =
//                    new LoginService(request.getServletContext());
//
//            UserVO login = service.login(userId, password);
//
//            if (login != null) {
//                HttpSession session = request.getSession();
//                session.setAttribute("loginMember", login);
//
//                return "redirect:/reservationMain.do";
//            } else {
//                request.setAttribute("errorMsg", "아이디 또는 비밀번호가 틀렸습니다.");
//                return "login/login";
//            }
//        }

        return null;
    }
}
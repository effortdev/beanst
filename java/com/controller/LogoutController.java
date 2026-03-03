package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // 1. 세션 가져오기
        HttpSession session = request.getSession(false);
        
        // 2. 세션이 존재하면 무효화(로그아웃)
        if (session != null) {
            session.invalidate();
        }

        // 3. 메인 페이지로 리다이렉트
        return "redirect:/main.do";
    }
}
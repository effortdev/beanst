package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController implements Action {

    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {
        request.setAttribute("pageTitle", "Vinst Hotel");
        request.setAttribute("pageCss", "home_main");
        
        request.setAttribute("contentPage",
                "/WEB-INF/views/home/main.jsp");

        return "home/main"; // 이게 만든 화면을 layout.jsp 로 보내고 layout 은 그걸 브라우저로 출력하는 개념입니다
    }
}


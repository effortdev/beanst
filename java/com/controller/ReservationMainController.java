package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReservationMainController implements Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        request.setAttribute("contentPage",
                "/WEB-INF/views/reservation/reservationMain.jsp");
        request.setAttribute("pageCss", "reservationMain");

        return "reservation/reservationMain";
    }
}

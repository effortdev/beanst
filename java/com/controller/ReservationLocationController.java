package com.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReservationLocationController implements Action {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) {

        request.setAttribute("contentPage",
                "/WEB-INF/views/reservation/reservationLocation.jsp");
        request.setAttribute("pageCss", "reservationLocation");

        return "reservation/reservationLocation";
    }
}
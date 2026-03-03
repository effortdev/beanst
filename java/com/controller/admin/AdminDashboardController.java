package com.controller.admin;

import com.controller.Action;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminDashboardController implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "admin/dashboard";
    }
}
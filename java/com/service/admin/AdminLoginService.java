package com.service.admin;

import com.dao.AdminDAO;
import com.dto.AdminDTO;

import jakarta.servlet.ServletContext;


public class AdminLoginService {

    private ServletContext context;

    public AdminLoginService(ServletContext context) {
        this.context = context;
    }

    public AdminDTO login(String userId, String password) {
        AdminDAO dao = new AdminDAO(context);
        return dao.login(userId, password);
    }
}
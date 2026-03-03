package com.controller.admin;

import com.controller.Action;
import com.dto.AdminDTO;
import com.service.admin.AdminLoginService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminLoginController implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String method = request.getMethod();

    
        if (method.equals("GET")) {
            return "admin/login";
        }


        if (method.equals("POST")) {

            String userId = request.getParameter("user_id");
            String password = request.getParameter("password");

            AdminLoginService service =
                    new AdminLoginService(request.getServletContext());

            AdminDTO admin = service.login(userId, password);
            
            System.out.println("AdminLoginController 진입");

            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);

                return "redirect:/admin/dashboard.do";
            } else {
                request.setAttribute("errorMsg", "아이디 또는 비밀번호가 틀렸습니다.");
                return "admin/login";
            }
        }

        return null;
    }
}
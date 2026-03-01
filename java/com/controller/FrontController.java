package com.controller;

import java.io.IOException;

import com.handler.HandlerMapper;
import com.util.SqlManager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapper mapper;

   

    
    
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }
    
    

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        doProcess(request, response);
    }
    
    
    

    private void doProcess(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String path = request.getServletPath();  // ex) /main.do

        Action controller = mapper.getController(path);

        String viewName = controller.execute(request, response);

        // redirect 처리
        if (viewName.startsWith("redirect:")) {
            response.sendRedirect(
                request.getContextPath() +
                viewName.replace("redirect:", "")
            );
        } else {
            RequestDispatcher rd =
                request.getRequestDispatcher(
                    "/WEB-INF/views/" + viewName + ".jsp"
                );
            rd.forward(request, response);
        }
    }
    
    
    @Override
    public void init() throws ServletException {
    	mapper = new HandlerMapper();
        SqlManager.load(getServletContext());
    }
}
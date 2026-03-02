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

	@Override
	public void init() throws ServletException {
		mapper = new HandlerMapper();
		SqlManager.load(getServletContext());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = uri.substring(contextPath.length());
		if (path == null || path.equals("") || path.equals("/")) {
			path = "/";
		}

		Action controller = mapper.getController(path);

		if (controller == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String viewName = controller.execute(request, response);

		// redirect 처리
		if (viewName.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + viewName.replace("redirect:", ""));
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + viewName + ".jsp");
			rd.forward(request, response);
		}
	}

}
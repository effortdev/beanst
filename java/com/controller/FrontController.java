package com.controller;

import java.io.IOException;

import com.handler.HandlerMapper;
import com.util.SqlManager;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
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
			path = "/main.do";
		}

		Action controller = mapper.getController(path);

		if (controller == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String viewName = controller.execute(request, response);

		if (viewName == null)
			return;

		if (viewName.startsWith("redirect:")) {
			response.sendRedirect(request.getContextPath() + viewName.replace("redirect:", ""));
			return;
		}

		request.setAttribute("contentPage", "/WEB-INF/views/" + viewName + ".jsp");

		String layout;

		if (viewName.startsWith("/admin/") || viewName.startsWith("admin/")) {
			layout = "/WEB-INF/views/admin/layout.jsp";
		} else {
			layout = "/WEB-INF/views/common/layout.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(layout);
		rd.forward(request, response);
	}

}
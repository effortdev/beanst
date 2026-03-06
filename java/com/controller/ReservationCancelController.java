package com.controller;

import java.util.ArrayList;
import java.util.List;

import com.dao.ReservationDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReservationCancelController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		try {

			String[] idArray = request.getParameterValues("reservationIds");

			if (idArray == null || idArray.length == 0) {

				response.setContentType("text/html;charset=UTF-8");

				response.getWriter().write("<script>alert('취소할 예약을 선택해주세요.');history.back();</script>");

				return null;
			}

			List<Integer> reservationIds = new ArrayList<>();

			for (String id : idArray) {

				reservationIds.add(Integer.parseInt(id));

			}

			ReservationDAO dao = new ReservationDAO(request.getServletContext());

			boolean result = dao.cancelReservations(reservationIds);

			if (result) {

				// 여기 핵심
				response.sendRedirect(request.getContextPath() + "/reservation/check.do");

			} else {

				response.setContentType("text/html;charset=UTF-8");

				response.getWriter().write("<script>" + "alert('취소 요청 실패');" + "history.back();" + "</script>");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;
	}
}
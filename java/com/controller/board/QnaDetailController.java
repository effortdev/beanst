package com.controller.board;

import com.controller.Action;
import com.dao.QnaDAO;
import com.dto.QnaDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaDetailController implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String noParam = request.getParameter("no");

		if (noParam == null || noParam.isEmpty()) {
			return "redirect:/qnaList.do";
		}

		int no;
		try {
			no = Integer.parseInt(noParam);
		} catch (NumberFormatException e) {
			return "redirect:/qnaList.do";
		}

		QnaDAO dao = new QnaDAO(request.getServletContext());
		QnaDTO qna = dao.selectQna(no);

		if (qna == null) {
			return "redirect:/qnaList.do";
		}

		if ("Y".equals(qna.getIsSecret())) {
			HttpSession session = request.getSession(false);
			Object loginUser = (session != null) ? session.getAttribute("loginUser") : null;

			boolean allowed = false;

			try {
				// loginUser는 프로젝트 상황에 따라 MemberDTO 또는 AdminDTO 일 수 있으므로 리플렉션 방식으로 최소 체크
				if (loginUser != null) {
					Class<?> clazz = loginUser.getClass();

					// memberNo 가 있으면 숫자 비교
					try {
						int memberNo = (int) clazz.getMethod("getMemberNo").invoke(loginUser);
						if (memberNo == qna.getMemberNo()) {
							allowed = true;
						}
					} catch (Exception ignore) {
					}

					// role 이 ADMIN 이면 허용
					if (!allowed) {
						try {
							String role = (String) clazz.getMethod("getRole").invoke(loginUser);
							if ("ADMIN".equals(role)) {
								allowed = true;
							}
						} catch (Exception ignore) {
						}
					}
				}
			} catch (Exception ignore) {
			}

			if (!allowed) {
				request.getSession().setAttribute("alertMsg", "비밀글은 작성자와 관리자만 볼 수 있습니다.");
				return "redirect:/qnaList.do";
			}
		}

		request.setAttribute("qna", qna);
		request.setAttribute("pageTitle", "Q&A 상세");
		request.setAttribute("pageCss", "board");
		request.setAttribute("contentPage", "/WEB-INF/views/board/qna/detail.jsp");

		return "board/qna/detail";
	}
}
package com.controller;

import com.service.UserService;
import com.vo.UserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JoinController implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();

        if (method.equals("GET")) {
            // 회원가입 전용 CSS 적용 지시
            request.setAttribute("pageCss", "join");
            
            return "member/join"; 
        }

        if (method.equals("POST")) {
            // 폼 데이터 파싱
            String userId = request.getParameter("user_id");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");

            UserVO userVO = new UserVO();
            userVO.setUserId(userId);
            userVO.setPassword(password);
            userVO.setEmail(email);
            userVO.setName(name);
            userVO.setPhone(phone);

            UserService service = new UserService(request.getServletContext());
            boolean isSuccess = service.joinUser(userVO);

            if (isSuccess) {
            	return "redirect:/member/joinComplete.do"; // 완료 페이지로 리다이렉트
            } else {
                // 실패 시 에러 메시지와 함께 다시 가입 페이지로 (예: 이메일 중복 등)
                request.setAttribute("errorMsg", "회원가입 처리 중 오류가 발생했습니다. (중복된 아이디나 이메일일 수 있습니다.)");
                request.setAttribute("pageCss", "join");
                return "member/join";
            }
        }

        return null;
    }
}
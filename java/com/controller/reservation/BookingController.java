package com.controller.reservation;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.controller.Action;
import com.service.BookingService;
import com.vo.RoomVO;
import com.vo.ReservationVO;
import com.vo.UserVO;

public class BookingController implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String method = request.getMethod();
        BookingService service = new BookingService(request.getServletContext());
        

        if (method.equals("GET")) {
            List<RoomVO> roomList = service.getAllRooms();
            
            List<ReservationVO> bookedList = service.getReservedDates();
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i = 0; i < bookedList.size(); i++) {
                ReservationVO r = bookedList.get(i);
                sb.append("{ roomId: ").append(r.getRoomId())
                  .append(", checkIn: '").append(r.getCheckIn())
                  .append("', checkOut: '").append(r.getCheckOut()).append("' }");
                if(i < bookedList.size() - 1) sb.append(", ");
            }
            sb.append("]");
            
            request.setAttribute("bookedListJson", sb.toString());
            request.setAttribute("roomList", roomList);
            request.setAttribute("pageCss", "reservationSub"); 
            return "reservation/booking";
        }

        if (method.equals("POST")) {
            System.out.println("==== [예약하기] POST 요청 도착 ====");
            
            try {
                HttpSession session = request.getSession();
                UserVO loginMember = (UserVO) session.getAttribute("loginMember");
                
                if (loginMember == null) {
                    System.out.println("에러: 세션에 로그인 정보가 없습니다.");
                    return "redirect:/login/login.do";
                }
                
                System.out.println("로그인 아이디 확인: " + loginMember.getUserId()); // ★ UserVO의 메서드명(getUserId)이 맞는지 꼭 확인하세요!

                // 파라미터가 잘 넘어오는지 콘솔에 출력
                System.out.println("room_id: " + request.getParameter("room_id"));
                System.out.println("check_in: " + request.getParameter("check_in"));
                System.out.println("check_out: " + request.getParameter("check_out"));
                System.out.println("adult_count: " + request.getParameter("adult_count"));
                System.out.println("child_count: " + request.getParameter("child_count"));
                System.out.println("total_price: " + request.getParameter("total_price"));

                // 파싱 시도 (여기서 NumberFormatException이 자주 발생합니다)
                int roomId = Integer.parseInt(request.getParameter("room_id"));
                String checkIn = request.getParameter("check_in");
                String checkOut = request.getParameter("check_out");
                String roomName = request.getParameter("room_name");
                int adultCount = Integer.parseInt(request.getParameter("adult_count"));
                int childCount = Integer.parseInt(request.getParameter("child_count"));
                int totalPrice = Integer.parseInt(request.getParameter("total_price"));

                ReservationVO reserveVO = new ReservationVO();
                reserveVO.setUserId(loginMember.getUserId()); 
                reserveVO.setRoomId(roomId);
                reserveVO.setRoomName(roomName);
                reserveVO.setName(loginMember.getName());
                reserveVO.setCheckIn(checkIn);
                reserveVO.setCheckOut(checkOut);
                reserveVO.setAdultCount(adultCount);
                reserveVO.setChildCount(childCount);
                reserveVO.setTotalPrice(totalPrice);
                
                
                boolean isAvailable = service.checkRoomAvailability(roomId, checkIn, checkOut);

                if (!isAvailable) {
                    // 예약 불가능할 경우: 에러 메시지를 담아서 다시 예약 페이지로 돌려보냅니다.
                    System.out.println("==== 예약 실패: 이미 예약된 날짜입니다 ====");
                    request.setAttribute("errorMsg", "선택하신 날짜에 이미 예약된 객실입니다. 다른 날짜나 객실을 선택해 주세요.");
                    
                    // 예약 폼을 다시 그려주기 위해 방 목록을 다시 가져와 담아줍니다.
                    List<RoomVO> roomList = service.getAllRooms();
                    request.setAttribute("roomList", roomList);
                    request.setAttribute("pageCss", "style"); 
                    
                    return "reservation/booking"; // 다시 예약 페이지 화면 띄우기
                }

                System.out.println("==== DB INSERT 시도 ====");
                boolean isSuccess = service.makeReservation(reserveVO);

                if (isSuccess) {
                    System.out.println("==== DB INSERT 성공 ====");
                    return "redirect:/reservation/complete.do";
                } else {
                    System.out.println("==== DB INSERT 실패 (Rollback 됨) ====");
                    request.setAttribute("errorMsg", "예약 처리 중 문제가 발생했습니다.");
                    return "redirect:/reservation/booking.do";
                }

            } catch (Exception e) {
                System.out.println("==== 컨트롤러 파싱 중 에러 발생 ====");
                e.printStackTrace();
                return "redirect:/reservation/booking.do";
            }
        }

        return null;
    }
}
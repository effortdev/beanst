package com.handler;

import java.util.HashMap;

import com.controller.Action;
import com.controller.HomeController;
import com.controller.MemberMypageController;
import com.controller.ReservationMainController;

public class HandlerMapper {

    private HashMap<String, Action> map = new HashMap<>();

    public HandlerMapper() {
    	map.put("/main.do", new HomeController());         // 메인
    	map.put("/member/mypage.do", new MemberMypageController());
    	map.put("/reservationMain.do", new ReservationMainController());
    }

    public Action getController(String path) {
        return map.get(path);
    }
}
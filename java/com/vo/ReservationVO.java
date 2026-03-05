package com.vo;

public class ReservationVO {
    private int reservationId;
    private String userId;      // 예약자 ID (세션에서 가져옴)
    private int roomId;         // 폼에서 전송됨
    private String roomName;
    private String name;
    private String checkIn;     // 폼에서 전송됨 (yyyy-mm-dd)
    private String checkOut;    // 폼에서 전송됨 (yyyy-mm-dd)
    private int adultCount;     // 폼에서 전송됨
    private int childCount;     // 폼에서 전송됨
    private int totalPrice;     // 폼에서 전송됨
    private String status;      // 기본값 '예약완료'

    // Getter & Setter
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCheckIn() { return checkIn; }
    public void setCheckIn(String checkIn) { this.checkIn = checkIn; }
    
    public String getCheckOut() { return checkOut; }
    public void setCheckOut(String checkOut) { this.checkOut = checkOut; }
    
    public int getAdultCount() { return adultCount; }
    public void setAdultCount(int adultCount) { this.adultCount = adultCount; }
    
    public int getChildCount() { return childCount; }
    public void setChildCount(int childCount) { this.childCount = childCount; }
    
    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
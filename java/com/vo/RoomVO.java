package com.vo;

public class RoomVO {
	private int roomId;
	private String roomName;
	private int baseCapacity;
	private int maxCapacity;
	private int basePrice;
	private int extraCharge;
	private String roomImg;

	// Getter & Setter
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getBaseCapacity() {
		return baseCapacity;
	}

	public void setBaseCapacity(int baseCapacity) {
		this.baseCapacity = baseCapacity;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}

	public int getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(int extraCharge) {
		this.extraCharge = extraCharge;
	}

	public String getRoomImg() {
		return roomImg;
	}

	public void setRoomImg(String roomImg) {
		this.roomImg = roomImg;
	}
}
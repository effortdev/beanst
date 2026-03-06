package com.vo;

import java.util.Date;

public class RoomManegeVO {
	private int room_id;
	private String room_name;
	private String capacity;
	private String room_location;
	private String room_description;
	private String usage_time;
	private String amenity;
	private String minibar;
	private Date created_at;
	private Date updated_at;

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getRoom_name() {
		return room_name;
	}

	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getRoom_location() {
		return room_location;
	}

	public void setRoom_location(String room_location) {
		this.room_location = room_location;
	}

	public String getRoom_description() {
		return room_description;
	}

	public void setRoom_description(String room_description) {
		this.room_description = room_description;
	}

	public String getUsage_time() {
		return usage_time;
	}

	public void setUsage_time(String usage_time) {
		this.usage_time = usage_time;
	}

	public String getAmenity() {
		return amenity;
	}

	public void setAmenity(String amenity) {
		this.amenity = amenity;
	}

	public String getMinibar() {
		return minibar;
	}

	public void setMinibar(String minibar) {
		this.minibar = minibar;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

}
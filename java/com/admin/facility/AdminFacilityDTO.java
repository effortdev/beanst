package com.admin.facility;

public class AdminFacilityDTO {
	private int facilityId;
	private String facilityType;
	private String facilityName;
	private String location;
	private String openTime;
	private String description;

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AdminFacilityDTO [facilityId=" + facilityId + ", facilityType=" + facilityType + ", facilityName="
				+ facilityName + ", location=" + location + ", openTime=" + openTime + ", description=" + description
				+ "]";
	}

}
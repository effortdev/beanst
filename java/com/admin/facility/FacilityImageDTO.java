package com.admin.facility;

public class FacilityImageDTO {
    private int imageId;
    private int facilityId;
    private String imagePath;
    private String isMain;
    private int displayOrder;

    public int getImageId() { return imageId; }
    public void setImageId(int imageId) { this.imageId = imageId; }

    public int getFacilityId() { return facilityId; }
    public void setFacilityId(int facilityId) { this.facilityId = facilityId; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getIsMain() { return isMain; }
    public void setIsMain(String isMain) { this.isMain = isMain; }

    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
}
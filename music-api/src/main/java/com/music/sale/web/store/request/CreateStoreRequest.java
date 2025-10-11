package com.music.sale.web.store.request;

import com.music.sale.persistence.store.entity.StoreEntity.StoreStatus;

public class CreateStoreRequest {
    private String name;
    private String description;
    private String zipcode;
    private String baseAddress;
    private String detailAddress;
    private Double latitude;
    private Double longitude;
    private String contactNumber;
    private String businessNumber;
    private String imageUrl;
    private StoreStatus status;
    private Long sellerId;

    // 기본 생성자
    public CreateStoreRequest() {}

    // 모든 필드 생성자
    public CreateStoreRequest(String name, String description, String zipcode,
                             String baseAddress, String detailAddress, Double latitude,
                             Double longitude, String contactNumber, String businessNumber,
                             String imageUrl, StoreStatus status, Long sellerId) {
        this.name = name;
        this.description = description;
        this.zipcode = zipcode;
        this.baseAddress = baseAddress;
        this.detailAddress = detailAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactNumber = contactNumber;
        this.businessNumber = businessNumber;
        this.imageUrl = imageUrl;
        this.status = status;
        this.sellerId = sellerId;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getZipcode() { return zipcode; }
    public void setZipcode(String zipcode) { this.zipcode = zipcode; }

    public String getBaseAddress() { return baseAddress; }
    public void setBaseAddress(String baseAddress) { this.baseAddress = baseAddress; }

    public String getDetailAddress() { return detailAddress; }
    public void setDetailAddress(String detailAddress) { this.detailAddress = detailAddress; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void getLongitude(Double longitude) { this.longitude = longitude; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getBusinessNumber() { return businessNumber; }
    public void setBusinessNumber(String businessNumber) { this.businessNumber = businessNumber; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public StoreStatus getStatus() { return status; }
    public void setStatus(StoreStatus status) { this.status = status; }

    public Long getSellerId() { return sellerId; }
    public void setSellerId(Long sellerId) { this.sellerId = sellerId; }
}

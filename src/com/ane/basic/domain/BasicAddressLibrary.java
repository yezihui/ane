package com.ane.basic.domain;

public class BasicAddressLibrary {
    private Integer addressLibrartId;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String address;
    
    private String fullAddress;

    public Integer getAddressLibrartId() {
        return addressLibrartId;
    }

    public void setAddressLibrartId(Integer addressLibrartId) {
        this.addressLibrartId = addressLibrartId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
}
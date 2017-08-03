package com.ane.basic.domain;

import java.util.Date;

import com.ane.main.domain.Main;

public class AuthOrganization extends Main {
    private Long id;			//网点id

    private String name;		//网点名称

    private String shortName;	//网点拼音

    private String country;		//所属国家

    private String province;	//所属省份

    private String city;		//所属城市

    private String region;		//所属区域

    private String address;		//地址
    
    private String fullAddress;

    private Integer siteState;	//网点状态(正常 失效)

    private Integer siteType;	//网点类型 (3普通网点 2二级分拨 1一级分拨 0总部)

    private Integer siteOrder;	//网点排序顺序

    private Long orderId;		//上级网点id
    
    private String orderName; 	//所属网点名

    private String headPhone;	//经理电话

    private String headName;	//经理名称

    private String bankName;	//开户名 -- 网点类似卡号

    private String bank;		//开户行 -- 类似

    private String xxx;			// 部门id

    private Integer creater;

    private Date created;

    private Integer updater;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
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

	public Integer getSiteState() {
        return siteState;
    }

    public void setSiteState(Integer siteState) {
        this.siteState = siteState;
    }

    public Integer getSiteType() {
        return siteType;
    }

    public void setSiteType(Integer siteType) {
        this.siteType = siteType;
    }

    public Integer getSiteOrder() {
        return siteOrder;
    }

    public void setSiteOrder(Integer siteOrder) {
        this.siteOrder = siteOrder;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getHeadPhone() {
        return headPhone;
    }

    public void setHeadPhone(String headPhone) {
        this.headPhone = headPhone == null ? null : headPhone.trim();
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName == null ? null : headName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx == null ? null : xxx.trim();
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
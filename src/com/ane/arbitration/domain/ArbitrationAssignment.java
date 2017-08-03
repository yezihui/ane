package com.ane.arbitration.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.ane.main.domain.Main;

public class ArbitrationAssignment extends Main{
    private Long id;

    private String code;

    private Long center;

    private Long ownerId;

    private String types;

    private String applyAddresses;

    private String ownerAddresses;

    private BigDecimal rate;

    private Long creater;

    private Date created;

    private Long updater;

    private Date updated;
    
    
    //添加的字段
    private String orderName;//仲裁人名
    
    private String applyProvince; //申报省份名
    
    private String ownerProvince; //责任省份名
    
    private String typeName; //仲裁类型名
    
    private String centerName; //仲裁中心名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Long getCenter() {
        return center;
    }

    public void setCenter(Long center) {
        this.center = center;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types == null ? null : types.trim();
    }

    public String getApplyAddresses() {
        return applyAddresses;
    }

    public void setApplyAddresses(String applyAddresses) {
        this.applyAddresses = applyAddresses == null ? null : applyAddresses.trim();
    }

    public String getOwnerAddresses() {
        return ownerAddresses;
    }

    public void setOwnerAddresses(String ownerAddresses) {
        this.ownerAddresses = ownerAddresses == null ? null : ownerAddresses.trim();
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getCreater() {
        return creater;
    }

    public void setCreater(Long creater) {
        this.creater = creater;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getApplyProvince() {
		return applyProvince;
	}

	public void setApplyProvince(String applyProvince) {
		this.applyProvince = applyProvince;
	}

	public String getOwnerProvince() {
		return ownerProvince;
	}

	public void setOwnerProvince(String ownerProvince) {
		this.ownerProvince = ownerProvince;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
    
}
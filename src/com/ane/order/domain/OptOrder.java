package com.ane.order.domain;

import java.util.Date;

import com.ane.main.domain.Se;

public class OptOrder extends Se {
    private Long orderId;

    private Integer produceTypeId;

    private Integer serviceTypeId;

    private Date sendTime;

    private String sendCustomer;

    private String sendPhone;

    private Integer sendCustomerAddressId;

    private String receiveCustomer;

    private String receivePhone;

    private Integer receiveCustomerAddressId;

    private String produceName;

    private String pickName;

    private Double calcWeight;

    private Double vol;

    private Integer piece;

    private Integer payModeId;

    private Double freight;

    private Double amount;

    private String remark;
    
    private Integer state;

    private Integer createdBy;

    private Date createdTime;

    private Integer modifiedBy;

    private Date modifiedTime;
    
    /**
     * 额外 需要的 字段
     * 寄件时间 字符串类型的
     */
    private String sendDate;
    
    private Integer province;
    
    private Integer city;
    
    private Integer area;
    
    private Integer p;
    
    private Integer c;
    
    private Integer a;

    private String sendAddress;
    
    private String receiveAddress;
    
    private String ewbNo;
    
    private String produceType;
    
    private String serviceType;
    
    private String payMode;
    
    private Long loginSiteId;
    
    private String beginName;
    private String endName;
    private String signName;
    
    public String getBeginName() {
		return beginName;
	}

	public void setBeginName(String beginName) {
		this.beginName = beginName;
	}

	public String getEndName() {
		return endName;
	}

	public void setEndName(String endName) {
		this.endName = endName;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getProduceTypeId() {
        return produceTypeId;
    }

    public void setProduceTypeId(Integer produceTypeId) {
        this.produceTypeId = produceTypeId;
    }

    public Integer getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(Integer serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendCustomer() {
        return sendCustomer;
    }

    public void setSendCustomer(String sendCustomer) {
        this.sendCustomer = sendCustomer == null ? null : sendCustomer.trim();
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone == null ? null : sendPhone.trim();
    }

    public Integer getSendCustomerAddressId() {
        return sendCustomerAddressId;
    }

    public void setSendCustomerAddressId(Integer sendCustomerAddressId) {
        this.sendCustomerAddressId = sendCustomerAddressId;
    }

    public String getReceiveCustomer() {
        return receiveCustomer;
    }

    public void setReceiveCustomer(String receiveCustomer) {
        this.receiveCustomer = receiveCustomer == null ? null : receiveCustomer.trim();
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone == null ? null : receivePhone.trim();
    }

    public Integer getReceiveCustomerAddressId() {
        return receiveCustomerAddressId;
    }

    public void setReceiveCustomerAddressId(Integer receiveCustomerAddressId) {
        this.receiveCustomerAddressId = receiveCustomerAddressId;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName == null ? null : produceName.trim();
    }

    public String getPickName() {
        return pickName;
    }

    public void setPickName(String pickName) {
        this.pickName = pickName == null ? null : pickName.trim();
    }

    public Double getCalcWeight() {
        return calcWeight;
    }

    public void setCalcWeight(Double calcWeight) {
        this.calcWeight = calcWeight;
    }

    public Double getVol() {
        return vol;
    }

    public void setVol(Double vol) {
        this.vol = vol;
    }

    public Integer getPiece() {
        return piece;
    }

    public void setPiece(Integer piece) {
        this.piece = piece;
    }

    public Integer getPayModeId() {
        return payModeId;
    }

    public void setPayModeId(Integer payModeId) {
        this.payModeId = payModeId;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getP() {
		return p;
	}

	public void setP(Integer p) {
		this.p = p;
	}

	public Integer getC() {
		return c;
	}

	public void setC(Integer c) {
		this.c = c;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public String getSendAddress() {
		return sendAddress;
	}

	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}

	public String getReceiveAddress() {
		return receiveAddress;
	}

	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}

	@Override
	public String toString() {
		return "OptOrder [orderId=" + orderId + ", produceTypeId="
				+ produceTypeId + ", serviceTypeId=" + serviceTypeId
				+ ", sendTime=" + sendTime + ", sendCustomer=" + sendCustomer
				+ ", sendPhone=" + sendPhone + ", sendCustomerAddressId="
				+ sendCustomerAddressId + ", receiveCustomer="
				+ receiveCustomer + ", receivePhone=" + receivePhone
				+ ", receiveCustomerAddressId=" + receiveCustomerAddressId
				+ ", produceName=" + produceName + ", pickName=" + pickName
				+ ", calcWeight=" + calcWeight + ", vol=" + vol + ", piece="
				+ piece + ", payModeId=" + payModeId + ", freight=" + freight
				+ ", amount=" + amount + ", remark=" + remark + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", modifiedBy="
				+ modifiedBy + ", modifiedTime=" + modifiedTime + ", sendDate="
				+ sendDate + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", p=" + p + ", c=" + c + ", a=" + a
				+ ", sendAddress=" + sendAddress + ", receiveAddress="
				+ receiveAddress + "]";
	}
	public OptOrder(){
		
	}

	public String getEwbNo() {
		return ewbNo;
	}

	public void setEwbNo(String ewbNo) {
		this.ewbNo = ewbNo;
	}

	public String getProduceType() {
		return produceType;
	}

	public void setProduceType(String produceType) {
		this.produceType = produceType;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	public Long getLoginSiteId() {
		return loginSiteId;
	}

	public void setLoginSiteId(Long loginSiteId) {
		this.loginSiteId = loginSiteId;
	}
}
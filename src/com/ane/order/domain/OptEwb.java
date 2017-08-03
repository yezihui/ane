package com.ane.order.domain;

import java.util.Date;

public class OptEwb {
    private String ewbNo;//运单号

    private Long orderId;//订单Id

    private Long sendSiteId;//寄件网点

    private Long receiveSiteId;//收件网点

    private Long sendEmployee;

    private Long receiveEmployee;

    private Integer state;//状态 0接单 1发件 2转件 3派送 4签收 5异常签收

    private Date createTime;

    private Date updateTime;
    
    private String sendSiteName;
    private String receiveSiteName;
    private String loginSiteName;
    private String sendTime;//寄件时间
    private String update;//签收时间
    private String produceName;//产品名称
    private String fullAddress;//地址
    private String payMode;
    private Double calcWeight;
    private Integer piece;

    public String getEwbNo() {
        return ewbNo;
    }

    public void setEwbNo(String ewbNo) {
        this.ewbNo = ewbNo == null ? null : ewbNo.trim();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSendSiteId() {
        return sendSiteId;
    }

    public void setSendSiteId(Long sendSiteId) {
        this.sendSiteId = sendSiteId;
    }

    public Long getReceiveSiteId() {
        return receiveSiteId;
    }

    public void setReceiveSiteId(Long receiveSiteId) {
        this.receiveSiteId = receiveSiteId;
    }

    public Long getSendEmployee() {
        return sendEmployee;
    }

    public void setSendEmployee(Long sendEmployee) {
        this.sendEmployee = sendEmployee;
    }

    public Long getReceiveEmployee() {
        return receiveEmployee;
    }

    public void setReceiveEmployee(Long receiveEmployee) {
        this.receiveEmployee = receiveEmployee;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getSendSiteName() {
		return sendSiteName;
	}

	public void setSendSiteName(String sendSiteName) {
		this.sendSiteName = sendSiteName;
	}

	public String getReceiveSiteName() {
		return receiveSiteName;
	}

	public void setReceiveSiteName(String receiveSiteName) {
		this.receiveSiteName = receiveSiteName;
	}

	public String getLoginSiteName() {
		return loginSiteName;
	}

	public void setLoginSiteName(String loginSiteName) {
		this.loginSiteName = loginSiteName;
	}

	

	@Override
	public String toString() {
		return "OptEwb [ewbNo=" + ewbNo + ", orderId=" + orderId
				+ ", sendSiteId=" + sendSiteId + ", receiveSiteId="
				+ receiveSiteId + ", sendEmployee=" + sendEmployee
				+ ", receiveEmployee=" + receiveEmployee + ", state=" + state
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", sendSiteName=" + sendSiteName + ", receiveSiteName="
				+ receiveSiteName + ", loginSiteName=" + loginSiteName
				+ ", sendTime=" + sendTime + ", update=" + update
				+ ", produceName=" + produceName + ", full_address="
				+ fullAddress + ", payModeId=" + payMode + ", calcWeight="
				+ calcWeight + ", piece=" + piece + "]";
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getProduceName() {
		return produceName;
	}

	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String full_address) {
		this.fullAddress = full_address;
	}

	public String getPayMode() {
		return payMode;
	}

	public void setPayMode(String payModeId) {
		this.payMode = payModeId;
	}

	public Double getCalcWeight() {
		return calcWeight;
	}

	public void setCalcWeight(Double calcWeight) {
		this.calcWeight = calcWeight;
	}

	public Integer getPiece() {
		return piece;
	}

	public void setPiece(Integer piece) {
		this.piece = piece;
	}
    
}
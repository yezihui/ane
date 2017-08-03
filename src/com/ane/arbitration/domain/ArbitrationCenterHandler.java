package com.ane.arbitration.domain;

import java.util.Date;

/**
 * 
 * @title:ArbitrationCenterHandler
 * @description:
 * @author yjx
 * @date:2016-9-12 下午3:28:22
 */
public class ArbitrationCenterHandler {
    private Long id;

    private Long arbitrationId;//仲裁id

    private Long organizationType;//组织类型
    
    private Long organizationType1;

    private String joinOrganization;

    private String joinOrganization1;
    
    private Long dutyType;

    private Long feeProject;
    
    private Long feeProject1;
    
    private Double feeMoney1;

    private Double feeMoney;

    private Date created;
    
    private String reason;
    
    private int state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArbitrationId() {
        return arbitrationId;
    }

    public void setArbitrationId(Long arbitrationId) {
        this.arbitrationId = arbitrationId;
    }

    public Long getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(Long organizationType) {
        this.organizationType = organizationType;
    }

    public String getJoinOrganization() {
        return joinOrganization;
    }

    public void setJoinOrganization(String joinOrganization) {
        this.joinOrganization = joinOrganization;
    }

    public Long getDutyType() {
        return dutyType;
    }

    public void setDutyType(Long dutyType) {
        this.dutyType = dutyType;
    }

    public Long getFeeProject() {
        return feeProject;
    }

    public void setFeeProject(Long feeProject) {
        this.feeProject = feeProject;
    }

    public Double getFeeMoney() {
        return feeMoney;
    }

    public void setFeeMoney(Double feeMoney) {
        this.feeMoney = feeMoney;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public Long getOrganizationType1() {
		return organizationType1;
	}

	public void setOrganizationType1(Long organizationType1) {
		this.organizationType1 = organizationType1;
	}

	public String getJoinOrganization1() {
		return joinOrganization1;
	}

	public void setJoinOrganization1(String joinOrganization1) {
		this.joinOrganization1 = joinOrganization1;
	}

	public Long getFeeProject1() {
		return feeProject1;
	}

	public void setFeeProject1(Long feeProject1) {
		this.feeProject1 = feeProject1;
	}

	public Double getFeeMoney1() {
		return feeMoney1;
	}

	public void setFeeMoney1(Double feeMoney1) {
		this.feeMoney1 = feeMoney1;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
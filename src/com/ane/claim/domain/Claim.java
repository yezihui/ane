package com.ane.claim.domain;

import java.util.Date;

import com.ane.main.domain.Main;

public class Claim extends Main {
    private Long id;

    private String ewbNo;		

    private String productName;

    private String sendDate;

    private String applyTime;

    private String auditTime;

    private String applyType;

    private Double amount;

    private String claimName;

    private String claimPhone;

    private String applyReason;

    private String applyState;

    private String comfirmState;
    
    private Date created;
    
    private Long siteId;
    
    private String siteName;
    
    private boolean chk;
    
    private Double camount;
    
    private Double claimAmount;
    
    private String safe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEwbNo() {
        return ewbNo;
    }

    public void setEwbNo(String ewbno) {
        this.ewbNo = ewbno == null ? null : ewbno.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendTime) {
        this.sendDate = sendTime == null ? null : sendTime.trim();
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime == null ? null : applyTime.trim();
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime == null ? null : auditTime.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName == null ? null : claimName.trim();
    }

    public String getClaimPhone() {
        return claimPhone;
    }

    public void setClaimPhone(String claimPhone) {
        this.claimPhone = claimPhone == null ? null : claimPhone.trim();
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason == null ? null : applyReason.trim();
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState == null ? null : applyState.trim();
    }

    public String getComfirmState() {
        return comfirmState;
    }

    public void setComfirmState(String comfirmState) {
        this.comfirmState = comfirmState == null ? null : comfirmState.trim();
    }

	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Double getCamount() {
		return camount;
	}

	public void setCamount(Double camount) {
		this.camount = camount;
	}

	public Double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}
	
}
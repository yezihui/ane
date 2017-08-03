package com.ane.order.domain;

import java.util.Date;

public class OptRetreat {
    private Long id;

    private String ewbNo;

    private Long applySiteId;
    
    private String applySiteName;

    private Date applyTime;

    private String fullAddress;

    private String returnReason;

    private Long msgSiteId;
    
    private String msgSiteName;

    private String confirmBy;

    private Date confirmTime;

    private Long confirmSiteId;
    
    private String confirmSiteName;

    private String acceptStatus;
    
    private String receiveSiteName;
    
    private String start;
	private String end;
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEwbNo() {
        return ewbNo;
    }

    public void setEwbNo(String ewbNo) {
        this.ewbNo = ewbNo == null ? null : ewbNo.trim();
    }

    public Long getApplySiteId() {
        return applySiteId;
    }

    public void setApplySiteId(Long applySiteId) {
        this.applySiteId = applySiteId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress == null ? null : fullAddress.trim();
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason == null ? null : returnReason.trim();
    }

    public Long getMsgSiteId() {
        return msgSiteId;
    }

    public void setMsgSiteId(Long msgSiteId) {
        this.msgSiteId = msgSiteId;
    }

    public String getConfirmBy() {
        return confirmBy;
    }

    public void setConfirmBy(String confirmBy) {
        this.confirmBy = confirmBy;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Long getConfirmSiteId() {
        return confirmSiteId;
    }

    public void setConfirmSiteId(Long confirmSiteId) {
        this.confirmSiteId = confirmSiteId;
    }

    public String getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(String acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

	public String getReceiveSiteName() {
		return receiveSiteName;
	}

	public void setReceiveSiteName(String receiveSiteName) {
		this.receiveSiteName = receiveSiteName;
	}

	public String getApplySiteName() {
		return applySiteName;
	}

	public void setApplySiteName(String applySiteName) {
		this.applySiteName = applySiteName;
	}

	public String getMsgSiteName() {
		return msgSiteName;
	}

	public void setMsgSiteName(String msgSiteName) {
		this.msgSiteName = msgSiteName;
	}

	public String getConfirmSiteName() {
		return confirmSiteName;
	}

	public void setConfirmSiteName(String confirmSiteName) {
		this.confirmSiteName = confirmSiteName;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
    
}
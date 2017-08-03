package com.ane.order.domain;

import java.util.Date;

public class OptTrail {
    private Integer id;
    
    private String ewbNo;

    private String topSite;

    private String nextSite;
    
    private Integer state;//状态 0接单 1发件 2到件 3派送 4签收 5异常签收

    private Date createTime;

    private Long createName;
    
    private String cName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEwbNo() {
		return ewbNo;
	}

	public void setEwbNo(String ewbNo) {
		this.ewbNo = ewbNo;
	}

	public String getTopSite() {
        return topSite;
    }

    public void setTopSite(String topSite) {
        this.topSite = topSite;
    }

    public String getNextSite() {
        return nextSite;
    }

    public void setNextSite(String nextSite) {
        this.nextSite = nextSite;
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

    public Long getCreateName() {
        return createName;
    }

    public void setCreateName(Long createName) {
        this.createName = createName;
    }

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
    
}
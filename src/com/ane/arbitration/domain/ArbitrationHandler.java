
package com.ane.arbitration.domain;
import java.util.Date;

import javax.persistence.Table;

/**
 * @title:ArbitrationHandler
 * @description:仲裁处理人信息实体类
 * @author:view
 * @date:2016-9-1 上午11:41:46
 */
@Table( name = "arbitration_handler")
public class ArbitrationHandler { 
	
	//空构造函数
	public ArbitrationHandler()
	{
	   
	}
	
   private Long id;
   
   private String centerHandOne;
   
   private String centerHandTwo;
	
    private Long arbitrationId;			    //备注2
	public Long getArbitrationId() {
		return arbitrationId;
	}
	
	public void setArbitrationId(Long arbitrationId) {
		this.arbitrationId = arbitrationId;
	}
	
	private String reason;			//备注3
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
    private Long handler;			    //备注4
    private String hand;
    
	public String getHand() {
		return hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public Long getHandler() {
		return handler;
	}
	
	public void setHandler(Long handler) {
		this.handler = handler;
	}
	
	private String provStatus;			//备注5
	public String getProvStatus() {
		return provStatus;
	}
	
	public void setProvStatus(String provStatus) {
		this.provStatus = provStatus;
	}
	private String status;			//备注6
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
    private Date created;			//备注7
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCenterHandOne() {
		return centerHandOne;
	}

	public void setCenterHandOne(String centerHandOne) {
		this.centerHandOne = centerHandOne;
	}

	public String getCenterHandTwo() {
		return centerHandTwo;
	}

	public void setCenterHandTwo(String centerHandTwo) {
		this.centerHandTwo = centerHandTwo;
	}
	
}

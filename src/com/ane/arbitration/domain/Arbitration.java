package com.ane.arbitration.domain;

import java.util.Date;

import javax.persistence.Table;

import com.ane.main.domain.Main;

/** 
 * 说明：仲裁主表 实体类
 * 创建人：View
 * 创建时间：2016-09-02
 */
@Table( name = "arbitration")
public class Arbitration extends Main { 
	
	
    //空构造函数
	public Arbitration()
	{
	   
	}
	//额外查询字段
	private String start;
	private String end;
	private boolean chk;
	private int  delay;
	private boolean cover;
	private Long id	;	    //id
	private String ewbNo; //运单号
	private Long applySiteId;			    //申请网点Id
	private String applySiteName;			//申请网点名
	private Long ownerSiteId;			    //责任网点Id
	private String ownerSiteName;			//责任网点名
	private String applyReason;				//申请原因
	private String applyType;				//责任类型名
	private Long applyId;					//申请类型Id
	private String signName;				//签收状态
	private Date sendTime;					//发件时间
	private Long handler;			    	//仲裁人
	private String handName;				//仲裁人名
	private String beginStation;			//开始站点
	private String endStation;				//终点
	private String productName;				//产品名
	private String packType;				//包装类型
	private Double amount;					//钱？
	private String complaintPhone;			//投诉人电话
	private String complainant;			    //投诉人名
	private String sender;					//发件人
	private String sendPhone;				//发件电话
	private String sendAddress;				//发件地址
	private String recipient;				//收件人
	private String recipientPhone;			//收件电话
	private String recipientAddress;		//收件地址
	private String productType;				//产品类型
	private int piece;				//件数
	private Double calcWeight;		//重量
    private Long creater;			    //备注26
    private Date created;			//备注27
    private Long updater;			    //备注28
    private Date updated;			//备注29
    private String status;			// 1 已分中心
    private String state;
    
    public static final String YFZX = "1"; //已分中心
    public static final String YJD = "2";//已接单 处理中
    public static final String CLWB = "3";//处理完毕，待审核
    public static final String SPBTG = "5";//仲裁审批不通过
    public static final String ZXCLZ = "6";//中心处理中
    public static final String JSTG = "7";//结算审核通过
    public static final String BYJD = "8";//不予接单
    
    
    private String sendDate;
    private String applyTime;
    private String auditTime; 
	
    public String getEwbNo() {
		return ewbNo;
	}

	public void setEwbNo(String ewbNo) {
		this.ewbNo = ewbNo;
	}
	
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getApplySiteId() {
		return applySiteId;
	}
	
	public void setApplySiteId(Long applySiteId) {
		this.applySiteId = applySiteId;
	}
	
    
	public Long getOwnerSiteId() {
		return ownerSiteId;
	}
	
	public void setOwnerSiteId(Long ownerSiteId) {
		this.ownerSiteId = ownerSiteId;
	}
	
	
	public String getApplyReason() {
		return applyReason;
	}
	
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public String getApplyType() {
		return applyType;
	}
	
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	
	public Long getHandler() {
		return handler;
	}
	
	public void setHandler(Long handler) {
		this.handler = handler;
	}
	
	public String getBeginStation() {
		return beginStation;
	}
	
	public void setBeginStation(String beginStation) {
		this.beginStation = beginStation;
	}
	
	public String getEndStation() {
		return endStation;
	}
	
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getPackType() {
		return packType;
	}
	
	public void setPackType(String packType) {
		this.packType = packType;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public String getComplaintPhone() {
		return complaintPhone;
	}
	
	public void setComplaintPhone(String complaintPhone) {
		this.complaintPhone = complaintPhone;
	}
    
	public String getComplainant() {
		return complainant;
	}
	
	public void setComplainant(String complainant) {
		this.complainant = complainant;
	}
	
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getSendPhone() {
		return sendPhone;
	}
	
	public void setSendPhone(String sendPhone) {
		this.sendPhone = sendPhone;
	}
	
	public String getSendAddress() {
		return sendAddress;
	}
	
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public String getRecipientPhone() {
		return recipientPhone;
	}
	
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	
	public String getRecipientAddress() {
		return recipientAddress;
	}
	
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isCover() {
		return cover;
	}

	public void setCover(boolean cover) {
		this.cover = cover;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public String getApplySiteName() {
		return applySiteName;
	}

	public void setApplySiteName(String applySiteName) {
		this.applySiteName = applySiteName;
	}

	public String getOwnerSiteName() {
		return ownerSiteName;
	}

	public void setOwnerSiteName(String ownerSiteName) {
		this.ownerSiteName = ownerSiteName;
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

	public boolean isChk() {
		return chk;
	}

	public void setChk(boolean chk) {
		this.chk = chk;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}

	public Double getCalcWeight() {
		return calcWeight;
	}

	public void setCalcWeight(Double calcWeight) {
		this.calcWeight = calcWeight;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getHandName() {
		return handName;
	}

	public void setHandName(String handName) {
		this.handName = handName;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
}

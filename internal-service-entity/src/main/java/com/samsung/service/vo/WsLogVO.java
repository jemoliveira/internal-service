package com.samsung.service.vo;

import java.util.Date;

public class WsLogVO {
		
	private String user;	
	private String company;	
	private String ascNo;		
	private String actionType;		
	private String errCode;
	private String errDesc;	
	private Date runTime;	
	private Date outputRunTime;	
	private String xml;	
	private String outputXml;		
	private String serverMode;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAscNo() {
		return ascNo;
	}

	public void setAscNo(String ascNo) {
		this.ascNo = ascNo;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public Date getOutputRunTime() {
		return outputRunTime;
	}

	public void setOutputRunTime(Date outputRunTime) {
		this.outputRunTime = outputRunTime;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public String getOutputXml() {
		return outputXml;
	}

	public void setOutputXml(String outputXml) {
		this.outputXml = outputXml;
	}

	public String getServerMode() {
		return serverMode;
	}

	public void setServerMode(String serverMode) {
		this.serverMode = serverMode;
	}
	
}
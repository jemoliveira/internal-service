package com.samsung.service.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BAT_LOG", schema = "dbo", catalog = "BI")
public class BatLogBean implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5404429482259629730L;
	@Id
	@Column(name="BATCH_NAME")
	private String name;
	
	@Column(name="Success_Y_N")
	private String success;
	
	@Column(name="Message")
	private String message;
		
	@Column(name="UsedDate", columnDefinition="TIMESTAMPTZ")
	private Date usedDate;
	
	@Column(name="RunDateTime", columnDefinition="TIMESTAMPTZ")
	private Date runDateTime;
		
	@Column(name="Company")
	private String company;
	
	@Column(name="table_name")
	private String tableName;
	
	@Column(name="id_user")
	private String idUser;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getUsedDate() {
		return usedDate;
	}
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}
	public Date getRunDateTime() {
		return runDateTime;
	}
	public void setRunDateTime(Date runDateTime) {
		this.runDateTime = runDateTime;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getIdUser() {
		return idUser;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
}
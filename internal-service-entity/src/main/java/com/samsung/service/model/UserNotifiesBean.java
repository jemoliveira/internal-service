package com.samsung.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_NOTIFIES_USER", schema = "dbo", catalog = "BI")
public class UserNotifiesBean {

	/**
	 * @serialField
	 */
	private static final long serialVersionUID = -447479175985587461L;

	@Id
	@Column(name="ID")
	private int id;

	@Column(name="USER_NAME")
	private String username;

	@Column(name="PHONE")
	private String phone;

	@Column(name="EMAIL", nullable=false)
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

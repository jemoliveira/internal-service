package com.samsung.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERNAL_USER_SYSTEM", schema="dbo",  catalog="BI")
public class UserBean {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private int id;

    @Column(name="NAME_INTERNAL")
    private String name;
    
    @Column(name="PASSWORD_INTERNAL")
    private String password;
    
    @Column(name="EMAIL_INTERNAL")
    private String email;
    
    @Column(name="ENABLED_INTERNAL")
    private String Enabled;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnabled() {
		return Enabled;
	}

	public void setEnabled(String enabled) {
		Enabled = enabled;
	}

}
package com.vibiya.model;

import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "AdminDetails", uniqueConstraints = { @UniqueConstraint(columnNames = "mobileNumber"),
		@UniqueConstraint(columnNames = "adminPassword") })
public class Admin {

	@Id
	@Column(unique = true)
	private Integer adminId;
	private String adminName;
	@Column(unique = true)
	@Size(min = 10, max = 10)
	private String mobileNumber;
	@Column(unique = true)
	private String adminPassword;

	Admin() {

	}

	public Admin(Integer adminId, String adminName, @Size(min = 10, max = 10) String mobileNumber,
			String adminPassword) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.mobileNumber = mobileNumber;
		this.adminPassword = adminPassword;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}

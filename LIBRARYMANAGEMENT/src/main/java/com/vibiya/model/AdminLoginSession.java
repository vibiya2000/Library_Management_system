package com.vibiya.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AdminLoginSession {

	@Id
	@Column(unique = true)
	private Integer adminId;
	private String uuid;
	private LocalDateTime adminLoginTime;

	public AdminLoginSession() {

	}

	public AdminLoginSession(Integer adminId, String uuid, LocalDateTime adminLoginTime) {
		super();
		this.adminId = adminId;
		this.uuid = uuid;
		this.adminLoginTime = adminLoginTime;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getAdminLoginTime() {
		return adminLoginTime;
	}

	public void setAdminLoginTime(LocalDateTime adminLoginTime) {
		this.adminLoginTime = adminLoginTime;
	}

}

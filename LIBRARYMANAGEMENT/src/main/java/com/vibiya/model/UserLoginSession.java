package com.vibiya.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserLoginSession {

	@Id
	@Column(unique = true)
	private Integer userId;
	private String uuid;
	private LocalDateTime userLoginTime;

	public UserLoginSession() {

	}

	public UserLoginSession(Integer userId, String uuid, LocalDateTime userLoginTime) {
		super();
		this.userId = userId;
		this.uuid = uuid;
		this.userLoginTime = userLoginTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getUserLoginTime() {
		return userLoginTime;
	}

	public void setUserLoginTime(LocalDateTime userLoginTime) {
		this.userLoginTime = userLoginTime;
	}

}

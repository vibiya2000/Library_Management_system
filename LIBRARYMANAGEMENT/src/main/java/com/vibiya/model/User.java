package com.vibiya.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "UserDetails", uniqueConstraints = { @UniqueConstraint(columnNames = "mobile"),
		@UniqueConstraint(columnNames = "userPassword") })
public class User {

	@Id
	@Column(unique = true)
	private Integer userId;
	private String userName;
	@Column(unique = true)
	@Size(min = 10, max = 10)
	private String mobile;
	@Column(unique = true)
	private String userPassword;

	User() {

	}

	public User(int userId, String userName, String mobile, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobile = mobile;
		this.userPassword = userPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}

package com.vibiya.DTO;

import jakarta.validation.constraints.NotNull;

public class UserLoginDTO {

	@NotNull(message = "Mobile number should not be null")
	private String mobile;
	
	@NotNull(message="password should not be null")
	private String userPassword;

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

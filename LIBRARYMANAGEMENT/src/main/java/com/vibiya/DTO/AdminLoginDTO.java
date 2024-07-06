package com.vibiya.DTO;

import jakarta.validation.constraints.NotNull;

public class AdminLoginDTO {

    @NotNull(message = "Mobile number should not be null")
	private String mobileNumber;
	
	@NotNull(message="password should not be null")
	private String adminPassword;

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

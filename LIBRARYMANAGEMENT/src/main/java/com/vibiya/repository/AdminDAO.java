package com.vibiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.Admin;

public interface AdminDAO extends JpaRepository<Admin, Integer>{
	
	public Admin findByMobileNumber(String mobileNumber);

}

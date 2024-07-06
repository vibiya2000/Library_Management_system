package com.vibiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.AdminLoginSession;

public interface AdminSessionDAO extends JpaRepository<AdminLoginSession, Integer> {
	
	public AdminLoginSession findByUuid(String key);

		


}

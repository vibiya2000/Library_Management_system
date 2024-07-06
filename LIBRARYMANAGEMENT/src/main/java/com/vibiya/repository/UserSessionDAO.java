package com.vibiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.UserLoginSession;

public interface UserSessionDAO extends JpaRepository<UserLoginSession, Integer> {
	
	public UserLoginSession findByUuid(String key);


}

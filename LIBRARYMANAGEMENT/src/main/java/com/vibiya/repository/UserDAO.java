package com.vibiya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vibiya.model.User;

public interface UserDAO extends JpaRepository<User, Integer>{
	public User findByMobile(String mobile);

}

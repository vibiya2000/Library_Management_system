package com.vibiya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.LoginException;
import com.vibiya.exception.UserException;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.model.User;
import com.vibiya.model.UserLoginSession;
import com.vibiya.repository.AdminSessionDAO;
import com.vibiya.repository.UserDAO;
import com.vibiya.repository.UserSessionDAO;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	UserSessionDAO userSessionDAO;
	@Autowired
	AdminSessionDAO adminSessionDAO;
	
	public User addUser(User user) throws UserException
	{
		Optional<User> existingUser =userDAO.findById(user.getUserId());
		if(existingUser.isPresent())
		{
			throw new UserException("User already exists");
		}
		return userDAO.save(user);
	}
	
	public User findById(Integer userId) throws UserException
	{
		Optional<User> existingUser= userDAO.findById(userId);
		if(existingUser.isEmpty())
		{
			throw new UserException("No such user exists");
		}
		return existingUser.get();
		
	}
	public List<User> findAllUser(String key) throws LoginException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		return userDAO.findAll();
	}

	public User updateUser(User user,String key) throws UserException, LoginException
	{
		UserLoginSession loggedInUser = userSessionDAO.findByUuid(key);
		if(loggedInUser==null)
		{
			throw new LoginException("Please Login first!!");
		}
		User existingUser = userDAO.findById(user.getUserId()).orElseThrow(()->new UserException("User with Id" +user.getUserId() +"not present"));
		
		existingUser.setUserName(user.getUserName());
		userDAO.save(existingUser);
		
		return existingUser; 
		
	}
	
	public void deleteUser(Integer userId,String key) throws LoginException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		userDAO.deleteById(userId);
	}
	
	


}

package com.vibiya.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.DTO.UserLoginDTO;
import com.vibiya.exception.LoginException;
import com.vibiya.model.User;
import com.vibiya.model.UserLoginSession;
import com.vibiya.repository.UserDAO;
import com.vibiya.repository.UserSessionDAO;

@Service
public class UserLoginService {
	
	@Autowired
	UserSessionDAO userSessionDAO;
	@Autowired
	UserDAO userDAO;
	
	public UserLoginSession logInUser(UserLoginDTO dto) throws LoginException
	{
		User existingUser =userDAO.findByMobile(dto.getMobile());
		if(existingUser==null)
		{
			throw new LoginException("Please enter valid mobile number");
		}
		
		Optional<UserLoginSession> validUserSessionOpt = userSessionDAO.findById(existingUser.getUserId());
		if(validUserSessionOpt.isPresent())
		{
			throw new LoginException("User already LoggedIn");
		}
		
		if(existingUser.getUserPassword().equals(dto.getUserPassword()))
		{
			String key = RandomStringUtils.randomNumeric(4);
			UserLoginSession userLoginSession = new UserLoginSession(existingUser.getUserId(),key,LocalDateTime.now());
			userSessionDAO.save(userLoginSession);
			
			return userLoginSession;
		}
		else throw new LoginException("Please provide valid password"); 
	}
	
	public String logOutUser(String key) throws LoginException
	{
		UserLoginSession validUserSession = userSessionDAO.findByUuid(key);
		if(validUserSession==null)
		{
			throw new LoginException("Admin not LoggedIn");
		}
		
		userSessionDAO.delete(validUserSession);
		
		return "LoggedOut Successfully";
	}
	
	

}

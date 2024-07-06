package com.vibiya.service;

import java.time.LocalDateTime;

import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.DTO.AdminLoginDTO;
import com.vibiya.model.Admin;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.repository.AdminDAO;
import com.vibiya.repository.AdminSessionDAO;

@Service
public class AdminLoginService {
	
	@Autowired
	private AdminSessionDAO adminSessionDao;
	
	@Autowired
	private AdminDAO adminDao;
	
	public AdminLoginSession logInAdmin(AdminLoginDTO dto) throws LoginException
	{
		Admin existingAdmin = adminDao.findByMobileNumber(dto.getMobileNumber());
		
		if(existingAdmin==null) throw new LoginException("Please provide valid mobile number");
		
		Optional<AdminLoginSession> validAdminSessionOpt = adminSessionDao.findById(existingAdmin.getAdminId());
		
		if(validAdminSessionOpt.isPresent())
		{
			throw new LoginException("Admin already loggedin with this number");		
		}
	
		if(existingAdmin.getAdminPassword().equals(dto.getAdminPassword()))
		{
			String key = RandomStringUtils.randomNumeric(4);
			
			AdminLoginSession adminLoginSession = new AdminLoginSession(existingAdmin.getAdminId(),key,LocalDateTime.now());
			
			adminSessionDao.save(adminLoginSession);
			return adminLoginSession;
			 
		}
		
		else throw new LoginException("Please enter valid password");
	 
		}	
	
	public String logoutAdmin(String key) throws LoginException
	{
		
		AdminLoginSession validAdminSession =adminSessionDao.findByUuid(key);
		
		if(validAdminSession==null) throw new LoginException("Admin not loggedIn");
		
		adminSessionDao.delete(validAdminSession);
		
		return "LoggedOut Successfully";
		
	}
	

}

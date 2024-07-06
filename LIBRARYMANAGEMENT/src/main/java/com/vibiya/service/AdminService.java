package com.vibiya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.LoginException;
import com.vibiya.model.Admin;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.repository.AdminDAO;
import com.vibiya.repository.AdminSessionDAO;


@Service
public class AdminService {
	
	@Autowired
	AdminSessionDAO adminSessionDAO;
	@Autowired
	AdminDAO adminDAO;
	
	public Admin addAdmin(Admin admin) throws AdminException
	{
		Optional<Admin> existingAdmin = adminDAO.findById(admin.getAdminId());
		if(existingAdmin.isPresent())
		{
			throw new AdminException("Admin already exists");
		}
		return adminDAO.save(admin);
	}
	
	public Admin updateAdmin(Admin admin,String key) throws LoginException, AdminException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		Optional<Admin> existingAdmin = adminDAO.findById(admin.getAdminId());
		if(existingAdmin==null)
		{
			throw new AdminException("No such Admin exists to update");
		}
		return adminDAO.save(admin);
	}
	
	public List<Admin> findAllAdmin(String key) throws LoginException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		return adminDAO.findAll();
	}
	
	public String deleteAdmin(Integer adminId,String key) throws LoginException
	{
		AdminLoginSession loggedInAdmin = adminSessionDAO.findByUuid(key);
		if(loggedInAdmin==null)
		{
			throw new LoginException("Please Login first!!");
		}
		adminDAO.deleteById(adminId);;
		return null;
	}

}

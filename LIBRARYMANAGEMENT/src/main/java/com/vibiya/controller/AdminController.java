package com.vibiya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.exception.AdminException;
import com.vibiya.exception.LoginException;
import com.vibiya.exception.UserException;
import com.vibiya.model.Admin;
import com.vibiya.service.AdminService;

import jakarta.validation.Valid;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	

	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) throws UserException, AdminException
	{
		return new ResponseEntity<Admin>(adminService.addAdmin(admin), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateAdmin")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin ,@RequestParam String key) throws UserException, LoginException, AdminException
	{
		return new ResponseEntity<Admin>(adminService.updateAdmin(admin,key), HttpStatus.OK);
	}
	
	@GetMapping("/findAllAdmin")
	public List<Admin> findAllAdmin(@RequestParam String key) throws LoginException
	{
		return adminService.findAllAdmin( key);
	}
	
	@DeleteMapping("/deleteAdmin/{adminId}")
	public ResponseEntity<String> deleteAdmin(@PathVariable Integer adminId,@RequestParam String key) throws LoginException
	{
		adminService.deleteAdmin(adminId,key);
		return new ResponseEntity<String>("Admin with Id "+adminId+" deleted succesfully", HttpStatus.OK);
	}
	

}

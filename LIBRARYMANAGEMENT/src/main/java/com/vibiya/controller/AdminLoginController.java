package com.vibiya.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.DTO.AdminLoginDTO;
import com.vibiya.model.AdminLoginSession;
import com.vibiya.service.AdminLoginService;

import jakarta.validation.Valid;

@RestController
public class AdminLoginController {

	
	@Autowired
	AdminLoginService adminLoginService;
	
	@PostMapping("/admin/login")
	public ResponseEntity<AdminLoginSession> logInAdmin(@Valid @RequestBody AdminLoginDTO dto) throws LoginException 
	{
		return new ResponseEntity<AdminLoginSession>(adminLoginService.logInAdmin(dto), HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/admin/logout")
	public String logOutAdmin(@RequestParam String key) throws LoginException
	{
		return adminLoginService.logoutAdmin(key);
	}

}

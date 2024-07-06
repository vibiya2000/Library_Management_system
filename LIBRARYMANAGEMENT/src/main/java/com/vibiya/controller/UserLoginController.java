package com.vibiya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vibiya.DTO.UserLoginDTO;
import com.vibiya.exception.LoginException;
import com.vibiya.model.UserLoginSession;
import com.vibiya.service.UserLoginService;

import jakarta.validation.Valid;

@RestController
public class UserLoginController {
	
	@Autowired
	UserLoginService userLoginService;
	
	@PostMapping("/user/login")
	public ResponseEntity<UserLoginSession> logInUser(@Valid @RequestBody UserLoginDTO dto) throws LoginException
	{
		return new ResponseEntity<UserLoginSession>(userLoginService.logInUser(dto), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/user/logout")
	public String logOutUser(@RequestParam String key) throws LoginException
	{
		return userLoginService.logOutUser(key);
	}
	

}

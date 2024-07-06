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

import com.vibiya.exception.LoginException;
import com.vibiya.exception.UserException;
import com.vibiya.model.User;
import com.vibiya.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws UserException
	{
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.CREATED);
	}
	
	@GetMapping("/findById/{userId}")
	public ResponseEntity<User> findById(Integer userId) throws UserException
	{
	
		return new ResponseEntity<User>(userService.findById(userId), HttpStatus.OK);
	
	}
	
	@GetMapping("/findAllUser")
	public List<User> findAllUser(@RequestParam String key) throws LoginException
	{
		return userService.findAllUser(key);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user,@RequestParam String key) throws UserException, LoginException
	{
		return new ResponseEntity<User>(userService.updateUser( user,key), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId,@RequestParam String key) throws LoginException
	{
		userService.deleteUser(userId,key);
		return new ResponseEntity<String>("User with Id "+userId+" deleted succesfully", HttpStatus.OK);
	}


}

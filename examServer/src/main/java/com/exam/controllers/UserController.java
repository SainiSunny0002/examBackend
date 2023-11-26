package com.exam.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.models.Roles;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//for creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception{
		
		user.setProfile("default.png");
		
		//encoding password with bcrypt encoder
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		Roles role = new Roles();
		role.setRoleId(2L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(userRole);
		
		return userService.createUser(user, roles);
	}
	
	//get user by username
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String userName) {
		return userService.getUser(userName);
	}
	
	//delete user by id
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
	}
	
	//update user by id
	
}

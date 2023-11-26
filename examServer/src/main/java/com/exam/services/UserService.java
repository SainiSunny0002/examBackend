package com.exam.services;

import java.util.Set;

import com.exam.models.User;
import com.exam.models.UserRole;

public interface UserService {

	//creeating user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	//get user by username
	public User getUser(String userName);
	//delete user by id
	public void deleteUser(Long userId);
}

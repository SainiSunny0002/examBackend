package com.exam.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.repositories.RoleRepository;
import com.exam.repositories.UserRepository;
import com.exam.services.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localUser = userRepository.findByUserName(user.getUsername());
		if(localUser != null) {
			System.out.println("User is already registered!!");
			throw new Exception("User is already present!!");
		}else {
			//user create
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			localUser = userRepository.save(user);
		}
		
		return localUser;
	}
	
	

	//getting user by username
	@Override
	public User getUser(String userName) {
		return userRepository.findByUserName(userName);
	}



	//deleting user by id
	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

}

package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.models.Roles;
import com.exam.models.User;
import com.exam.models.UserRole;
import com.exam.services.UserService;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner{
	
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting code!!");
//		User user = new User();
//		Roles roles1 = new Roles();
//		
//		user.setFirstName("Sunny");
//		user.setLastName("Saini");
//		user.setUserName("sunny0603");
//		user.setPassword(bCryptPasswordEncoder.encode("123"));
//		user.setEmail("sunny@gmail.com");
//		user.setProfile("default.png");
//		
//		roles1.setRoleId(1L);
//		roles1.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleset = new HashSet<UserRole>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(roles1);
//		userRole.setUser(user);
//		
//		userRoleset.add(userRole);
//		
//		User user1 = userService.createUser(user, userRoleset);
//		System.out.println("UserName >>>>>>>>>>> "+user1.getUserName());
		
	}

}

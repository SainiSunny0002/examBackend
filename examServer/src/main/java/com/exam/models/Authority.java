package com.exam.models;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority{
	
	private String authority;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return authority;
	}

}

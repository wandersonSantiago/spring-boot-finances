package com.api.personal.finance.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.api.personal.finance.model.User;

public class UserService {
	
	public static User authenticated() {
		try {
			return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}

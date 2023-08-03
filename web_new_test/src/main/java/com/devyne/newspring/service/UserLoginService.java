package com.devyne.newspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.devyne.newspring.vo.UserVO;

@Service
public class UserLoginService implements UserDetailsService {
	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserVO userDetails = new UserVO();
		
		
		return null;
	}
	
}

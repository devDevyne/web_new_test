package com.devyne.newspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.devyne.newspring.dao.UserDao;
import com.devyne.newspring.vo.UserVO;

@Service
public class UserLoginService implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	// loadUserByUsername 메소드를 통해 DB에서 사용자 정보를 가져와 권한을 부여. 
	@Override
	public UserVO loadUserByUsername(String email) throws UsernameNotFoundException {

		UserVO userDetails = new UserVO();

		try {
			UserVO userInfo = userDao.memberInfo(email); // email을 통해 user 정보 가져오기.

			if (userInfo == null) {
				return null;
			} else {
				List<GrantedAuthority> authorities = new ArrayList<>();

				userDetails.setName(userInfo.getName()); // name
				userDetails.setUsername(userInfo.getUsername()); // email
				userDetails.setPassword(userInfo.getPassword()); // password
				userDetails.setAuth(userInfo.getAuth());

				// auth에 value에 따라 접근 권한 부여
				if ("guest".equals(userInfo.getAuth())) {
					authorities.add(new SimpleGrantedAuthority("ROLE_GUEST"));

				} else if ("user".equals(userInfo.getAuth())) {
					authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

				} else if ("admin".equals(userInfo.getAuth())) {
					authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
				}
				
				// 권한 set
				userDetails.setAuthorities(authorities);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userDetails; //UserVO type return 
	}

}

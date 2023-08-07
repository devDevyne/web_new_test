package com.devyne.newspring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.devyne.newspring.service.UserLoginService;
import com.devyne.newspring.vo.UserVO;

@Component
public class UserLoginAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserLoginService userLoginService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Logger logger = LoggerFactory.getLogger(this.getClass().getPackage().getName());
		
		String userId = (String) authentication.getPrincipal();
		String userPw = (String) authentication.getCredentials();
		// 입력된 id와 pw 가져오기. 
		
		UserVO userDetails = userLoginService.loadUserByUsername(userId);
		
		// id(이메일)이나 pw가 다를 시 
		if(userDetails == null || !userId.equals(userDetails.getUsername()) || !userPw.equals(userDetails.getPassword())) {
			throw new BadCredentialsException(userId);
			
		  // 비활성된 계정일 경우 
		} else if (!userDetails.isEnabled()) {
			 throw new DisabledException(userId);
		}
		
		logger.debug("userId : " + userId + ", userPw : " + userPw);
		// 계정이 인증되었다면, token 객체에 입력한 정보와 부여된 권한을 리턴. 
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
	// AuthenticationProvider 인터페이스가 지정된 Authentication 객체를 지원하는 경우에 true를 리턴한다.
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}

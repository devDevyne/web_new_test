package com.devyne.newspring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class UserLoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
			
		if(exception instanceof BadCredentialsException) {
			// 아이디나 비밀번호를 잘못 입력했을 때
			request.setAttribute("errormsg", "아이디 또는 비밀번호가 틀립니다.");
			
		} else if(exception instanceof UsernameNotFoundException) {
			// 계정이 없을 때 
			request.setAttribute("errormsg", "존재하지 않는 사용자입니다.");
			
		} else if(exception instanceof InternalAuthenticationServiceException) {
			request.setAttribute("errormsg", "존재하지 않는 사용자입니다.");
			
		} else {
			request.setAttribute("errormsg", "알 수 없는 이유로 로그인이 실패했습니다.");
		}
				
		request.getRequestDispatcher("/auth/login").forward(request, response);
		// RequestDispatcher : 클라이언트로부터 요청을 받고, 이를 다른 리소스(서블릿, html, jsp)로 넘겨주는 역할. 
		// getRequestDispatcher : 해당 경로로 request를 전달. 
		// forward : 사용자 요청에 의해 컨테이너에서 생성된 request와 reponse를 다른 리소스로 넘겨주는 역할. 
	}
	
}

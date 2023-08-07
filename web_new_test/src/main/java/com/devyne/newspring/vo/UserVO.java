package com.devyne.newspring.vo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserVO implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4295715898628835754L;
	private String name;
	private String email;
	private String passwd;
	private String auth;
	
	// 계정 권한 목록. 
	public Collection<? extends GrantedAuthority> Authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Authorities;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> Authorities) {
		this.Authorities = Authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwd;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	public void setUsername(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.passwd = password;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
	
}

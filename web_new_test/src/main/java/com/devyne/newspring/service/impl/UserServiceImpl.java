package com.devyne.newspring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devyne.newspring.dao.UserDao;
import com.devyne.newspring.service.UserService;
import com.devyne.newspring.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public int userCreate(UserVO userVO) {
		return userDao.userCreate(userVO);
	}
	
	@Override
	public int checkEmail(String email) {
		return userDao.checkEmail(email);
	}
	
	@Override
	public List<UserVO> userList(UserVO userVO) {
		return userDao.userList(userVO);
	}
	
	@Override
	public UserVO userDetails(UserVO userVO) {
		return userDao.userDetails(userVO);
	}
	
	@Override
	public int userUpdate(UserVO userVO) {
		return userDao.userUpdate(userVO);
	}
	
	@Override
	public int userDelete(UserVO userVO) {
		return userDao.userDelete(userVO);
	}
	
	@Override 
	public int userSignUp(UserVO userVO) { 
		return userDao.userSignUp(userVO);
	}
	 
}

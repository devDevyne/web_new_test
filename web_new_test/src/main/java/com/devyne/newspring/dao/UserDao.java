package com.devyne.newspring.dao;

import java.util.List;

import com.devyne.newspring.vo.UserVO;

public interface UserDao {

	UserVO memberInfo(String email);

	int userCreate(UserVO userVO);

	int checkEmail(String email);

	List<UserVO> userList(UserVO userVO);

	UserVO userDetails(UserVO userVO);

	int userUpdate(UserVO userVO);

	int userDelete(UserVO userVO);
	
}

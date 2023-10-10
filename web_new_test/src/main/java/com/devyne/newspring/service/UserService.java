package com.devyne.newspring.service;

import java.util.List;

import com.devyne.newspring.vo.UserVO;

public interface UserService {

	int userCreate(UserVO userVO);

	int checkEmail(String email);

	List<UserVO> userList(UserVO userVO);

	UserVO userDetails(UserVO userVO);

	int userUpdate(UserVO userVO);

	int userDelete(UserVO userVO);

	int userSignUp(UserVO userVO);

}

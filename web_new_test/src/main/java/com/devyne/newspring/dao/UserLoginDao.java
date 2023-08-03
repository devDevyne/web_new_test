package com.devyne.newspring.dao;

import com.devyne.newspring.vo.UserVO;

public interface UserLoginDao {

	UserVO memberInfo(String email);

}

package com.devyne.newspring.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devyne.newspring.dao.UserLoginDao;
import com.devyne.newspring.vo.UserVO;

@Repository
public class UserLoginDaoImpl implements UserLoginDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public UserVO memberInfo(String email) {
		return sqlSession.selectOne("member_Info", email);
	}
	
	
}

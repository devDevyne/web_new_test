package com.devyne.newspring.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devyne.newspring.dao.UserDao;
import com.devyne.newspring.vo.UserVO;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public UserVO memberInfo(String email) {
		return sqlSession.selectOne("member_Info", email);
	}
	
	@Override
	public int userCreate(UserVO userVO) {
		return sqlSession.insert("user_create", userVO);
	}
	
	@Override
	public int checkEmail(String email) {
		return sqlSession.selectOne("check_email", email);
	}
	
	@Override
	public List<UserVO> userList(UserVO userVO) {
		return sqlSession.selectList("user_list", userVO);
	}
	
	@Override
	public UserVO userDetails(UserVO userVO) {
		return sqlSession.selectOne("user_details", userVO);
	}
}

package com.devyne.newspring.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devyne.newspring.dao.BookDao;
import com.devyne.newspring.vo.BookVO;

@Repository
public class BookDaoImpl implements BookDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Override
	public List<BookVO> selectList(BookVO bookVO) {
		return sqlSession.selectList("select_list", bookVO);
	}
	
	@Override
	public int insert(BookVO bookVO) {
		return sqlSession.insert("insert", bookVO);
	}
	
	@Override
	public BookVO detail(BookVO bookVO) {
		return sqlSession.selectOne("detail", bookVO);
	}
}

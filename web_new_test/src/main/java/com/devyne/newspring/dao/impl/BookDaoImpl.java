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
		// selectOne : 데이터를 한 개만 가져오고 싶을 때 (해당 bookId 대한 책 정보만 가져오기)
		// 쿼리 결과 행 수가 0개이면 null 반환
		// 여러 개면 TooManyResultsException 예외 발생
	}
	
	@Override
	public int update(BookVO bookVO) {
		return sqlSession.update("update", bookVO);
	}
	
	@Override 
	public int delete(BookVO bookVO) {
		return sqlSession.delete("delete", bookVO);
	}
}

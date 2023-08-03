package com.devyne.newspring.dao;

import java.util.List;

import com.devyne.newspring.vo.BookVO;

public interface BookDao {

	// 리스트
	List<BookVO> selectList(BookVO bookVO);

	// 등록
	int insert(BookVO bookVO);
	
	// 상세 
	BookVO detail(BookVO bookVO);

	int update(BookVO bookVO);

	int delete(BookVO bookVO);
}

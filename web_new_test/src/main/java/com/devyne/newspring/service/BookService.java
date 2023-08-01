package com.devyne.newspring.service;

import java.util.List;

import com.devyne.newspring.vo.BookVO;

public interface BookService {

	List<BookVO> list(BookVO bookVO);

	int insert(BookVO bookVO);

	BookVO detail(BookVO bookVO);
	
}

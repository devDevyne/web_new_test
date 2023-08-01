package com.devyne.newspring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devyne.newspring.dao.BookDao;
import com.devyne.newspring.service.BookService;
import com.devyne.newspring.vo.BookVO;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookDao bookDao;
	
	@Override
	public List<BookVO> list(BookVO bookVO) {
		return bookDao.selectList(bookVO);
	}
	
	@Override
	public int insert(BookVO bookVO) {
		return bookDao.insert(bookVO);
	}
	
	@Override
	public BookVO detail(BookVO bookVO) {
		return bookDao.detail(bookVO);
	}
}

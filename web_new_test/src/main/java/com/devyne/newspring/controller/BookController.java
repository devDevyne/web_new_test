package com.devyne.newspring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.devyne.newspring.service.BookService;
import com.devyne.newspring.vo.BookVO;

@Controller
public class BookController {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getPackage().getName());
	
	@Autowired
	BookService bookService;
	
	@GetMapping(value = "/list")
	public String list(Model model, BookVO bookVO) {
		logger.info("====================== 책 리스트 페이지 ======================");
		List<BookVO> bookListVO = bookService.list(bookVO);
		model.addAttribute("bookListVO", bookListVO);
		
		return "book/list";
	}
	
	// 등록 페이지 
	@GetMapping(value = "/insert")
	public String insert(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 등록 페이지 ======================");
		return "book/insert";
	}
	
	// 등록 처리
	@PostMapping(value = "/insBook")
	public String insBook(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 등록 처리 ======================");
		int book_insert = bookService.insert(bookVO);
		return "redirect:list";
	}
	
	// 상세 책 정보
	@GetMapping(value = "/detail/{book_id}")
	public String detail(@PathVariable("book_id") Integer book_id, Model model, BookVO bookVO) {
		BookVO bookDetailVO = bookService.detail(bookVO);
		
		book_id = bookDetailVO.getBook_id();
		model.addAttribute("bookDetailVO", bookDetailVO);
		return "board/detail";
	}
	
}

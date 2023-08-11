package com.devyne.newspring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devyne.newspring.service.BookService;
import com.devyne.newspring.vo.BookVO;
import com.devyne.newspring.vo.UserVO;

@Controller
public class BookController {
	private Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	@GetMapping(value = "/book/list")
	public String list(Model model, BookVO bookVO, Authentication authentication) {
		logger.info("====================== 책 리스트 페이지 ======================");
		List<BookVO> bookListVO = bookService.list(bookVO);
		model.addAttribute("bookListVO", bookListVO);

		UserVO userVO = (UserVO) authentication.getPrincipal();
		model.addAttribute("username", userVO.getName());

		String auth = userVO.getAuthorities().toString();
		if (auth.contains("ROLE_ADMIN")) {
			model.addAttribute("auth", "관리자");
		} else if (auth.contains("ROLE_USER")) {
			model.addAttribute("auth", "멤버");
		} else if (auth.contains("ROLE_GUEST")) {
			model.addAttribute("auth", "게스트");
		}

		return "book/list";
	}

	@GetMapping(value = "bookList")
	@ResponseBody
	public List<BookVO> bookList(BookVO bookVO) {
		List<BookVO> bookList = bookService.list(bookVO);
		return bookList;
	}

	// 등록 페이지
	@GetMapping(value = "/book/insert")
	public String insert(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 등록 페이지 ======================");
		return "book/insert";
	}

	// 등록 처리
	@PostMapping(value = "/book/insBook")
	public String insBook(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 등록 처리 ======================");
		int book_insert = bookService.insert(bookVO);

		return "redirect:/book/list";
	}

	// 상세 책 정보
	@GetMapping(value = "/book/detail")
	public String detail(Model model, BookVO bookVO) {
		logger.info("====================== 책 상세 정보 ======================");
		BookVO detailBookVO = bookService.detail(bookVO);
		int bookId = detailBookVO.getBookId();
		model.addAttribute("detailBookVO", detailBookVO);
		model.addAttribute("bookId", bookId);

		return "book/detail";
	}

	// 책 정보 수정 페이지
	@GetMapping(value = "/book/update")
	public String update(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 수정 페이지 ======================");
		BookVO detailBookVO = bookService.detail(bookVO);
		model.addAttribute("detailBookVO", detailBookVO);

		return "book/update";
	}

	// 책 정보 수정 처리
	@PostMapping(value = "/book/udtBook")
	public String udtBook(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 수정 처리 ======================");
		int update = bookService.update(bookVO);

		return "redirect:/book/detail?bookId=" + bookVO.getBookId();
	}

	// 책 정보 삭제
	@PostMapping(value = "/book/delete")
	public String delete(Model model, BookVO bookVO) {
		logger.info("====================== 책 정보 삭제 ======================");
		int delete = bookService.delete(bookVO);

		return "redirect:/book/list";
	}

};
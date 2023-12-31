package com.devyne.newspring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devyne.newspring.service.BookService;
import com.devyne.newspring.service.UserService;
import com.devyne.newspring.vo.BookVO;
import com.devyne.newspring.vo.UserVO;

@Controller
public class ExcelController {
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value= "/bookList_excel")
	@ResponseBody
	public void excelDownload(HttpServletResponse response, BookVO bookVO) throws IOException {
		List<BookVO> bookList = bookService.list(bookVO);
		
		XSSFWorkbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		wb = new XSSFWorkbook();
		sheet = wb.createSheet();
		
		int cellCount = 0;
		row = sheet.createRow(0);
		cell = row.createCell(cellCount++);
		cell.setCellValue("글번호");
		cell = row.createCell(cellCount++);
		cell.setCellValue("제목");
		cell = row.createCell(cellCount++);
		cell.setCellValue("카테고리");
		cell = row.createCell(cellCount++);
		cell.setCellValue("가격");

		for(int i=0; i<bookList.size(); i++) {
			row = sheet.createRow(i+1);
			cellCount = 0;
			cell = row.createCell(cellCount++);
			cell.setCellValue(bookList.get(i).getBookId());
			cell = row.createCell(cellCount++);
			cell.setCellValue(bookList.get(i).getTitle());
			cell = row.createCell(cellCount++);
			cell.setCellValue(bookList.get(i).getCategory());
			cell = row.createCell(cellCount++);
			cell.setCellValue(bookList.get(i).getPrice());
		}
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=bookList.xlsx");
		
		wb.write(response.getOutputStream());
		wb.close();
	}
	
	@GetMapping("/userList_excel")
	@ResponseBody
	public void userListtoExcel(HttpServletResponse response, UserVO userVO) throws IOException {
		List<UserVO> userList = userService.userList(userVO);
		
		XSSFWorkbook wb = null;
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		wb = new XSSFWorkbook();
		sheet = wb.createSheet();
		
		int cellCount = 0;
		row = sheet.createRow(0);
		cell = row.createCell(cellCount++);
		cell.setCellValue("이름");
		cell = row.createCell(cellCount++);
		cell.setCellValue("이메일");
		cell = row.createCell(cellCount++);
		cell.setCellValue("권한");
		
		for(int i=0; i<userList.size(); i++) {
			row = sheet.createRow(i+1);
			cellCount = 0;
			cell = row.createCell(cellCount++);
			cell.setCellValue(userList.get(i).getName());
			cell = row.createCell(cellCount++);
			cell.setCellValue(userList.get(i).getUsername());
			cell = row.createCell(cellCount++);
			cell.setCellValue(userList.get(i).getAuth());
		}
		
		response.setContentType("ms-vnd/excel");
		response.setHeader("Content-Disposition", "attachment;filename=userList.xlsx");
		
		wb.write(response.getOutputStream());
		wb.close();
	}
	
}

package com.devyne.newspring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devyne.newspring.service.UserService;
import com.devyne.newspring.vo.UserVO;

@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user/create")
	public String userCreate(Model model, UserVO userVO) {
		logger.info("====================== 신규 유저 등록 페이지 ======================");
		
		return "/user/create";
	}
	
	@PostMapping("/createUser")
	public String crtUser(Model model, UserVO userVO) {
		logger.info("====================== 신규 유저 등록 처리 ======================");
		
		int user_create =  userService.userCreate(userVO);
		
		
		return "redirect:/user/list";
		// userlist 나중에 수정. 
	}
	
	@PostMapping("/signUp")
	public String signUpUser(Model model, UserVO userVO) {
		logger.info("====================== 신규 유저 등록 처리 ======================");
		
		int user_signUp = userService.userSignUp(userVO);
		
		return "redirect:/auth/login";
	}
	

	@PostMapping("/check_email")
	@ResponseBody // view페이지가 아닌 반환값 그대로 요청을 보낸 클라이언트(jsp)에게 return 
	public String checkEmail(@RequestParam("email") String email) {
		String result = "N";
		logger.info(email);
		int count = userService.checkEmail(email);
		if(count == 0) { // 중복된 이메일이 없으면 Y를 리턴. 
			result = "Y";
		}
		return result;
	}
	
	@GetMapping("/user/list")
	public String userList(Model model, UserVO userVO) {
		List<UserVO> userList = userService.userList(userVO);
		model.addAttribute("userList", userList);
		
		return "/user/list";
	}
	
	@GetMapping("/user/detail")
	public String userDetail(Model model, UserVO userVO) {
		UserVO userDetails = userService.userDetails(userVO);
		model.addAttribute("userDetails", userDetails);
		return "/user/detail";
	}
	
	@GetMapping(value = "/user/update")
	public String userUpdate(Model model, UserVO userVO) {
		UserVO userDetails = userService.userDetails(userVO);
		String auth = userDetails.getAuth();
		model.addAttribute("userDetails", userDetails);
		model.addAttribute("auth", auth);
		return "user/update";
	}
	
	@PostMapping(value = "/user/udtUser")
	public String udtUser(Model model, UserVO userVO) {
		int update = userService.userUpdate(userVO);
		return "redirect:/user/detail?userId=" + userVO.getUserId();
	}
	
	@PostMapping(value = "/user/delete")
	public String delUser(Model model, UserVO userVO) {
		int delete = userService.userDelete(userVO);
		return "redirect:/user/list";
	}
}

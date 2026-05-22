package com.amy.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amy.demo.dto.UsersDTO;
import com.amy.demo.service.UsersService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
	private final UsersService usersService;
	
	@PostMapping("/addUser")
	public String addUser(Model model) {//Model是要回傳給前端的物件
		
		return "usersAdd";
		
	}
	
	
	@PostMapping("/addSubmit")
	public String addSubmit(UsersDTO dto, Model model) {//Model是要回傳給前端的物件
		int aaa=usersService.doCreate(dto);
		if(aaa == 1) {
			model.addAttribute("addMsg", "新增成功");
		}else {
			model.addAttribute("addMsg", "新增失敗");//String attributeName, Object attributeValue
		}
		
		return "usersAdd";
		
	}
	
}

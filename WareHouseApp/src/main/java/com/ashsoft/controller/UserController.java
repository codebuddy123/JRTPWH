package com.ashsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashsoft.model.User;
import com.ashsoft.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	// 1. Show Reg form

	@GetMapping("/register")
	public String showReg() {

		return "UserRegister";
	}

	// 2. Save User

	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model) {

		Integer uid = service.saveUser(user);

		String message = "User with ID: " + uid + " Saved Successfully...";

		model.addAttribute("msg", message);

		return "UserRegister";
	}

	// 3. Display Users

	@GetMapping("/all")
	public String displayUsers(Model model) {

		List<User> list = service.getAllUsers();

		model.addAttribute("list", list);

		return "UserData";
	}
}

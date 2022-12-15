package com.co.andresfot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/", "index"})
public class IndexController {
	
	@GetMapping
	public String mainView(Model model) {
		model.addAttribute("titulo", "This project is awesome and works!!!");
		return "index";
	}
	
}

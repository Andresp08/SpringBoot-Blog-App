package com.co.andresfot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

	@GetMapping("/nosotros")
	public String nosotrosPagina() {
		return "nosotros";
	}
}

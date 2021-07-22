package com.spring.project.root.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping({ "/", "/index" })
public class IndexController {
	
//	@GetMapping
//	public String main() {
//		return "index";
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String doGet(final Model model) {
		return "index/index";
	}
	
}

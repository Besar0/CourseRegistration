package com.spring.project.root.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/logout")
public class LogoutController {

	@RequestMapping(method = { RequestMethod.GET })
	public String doLogout(final Model model, HttpServletRequest request) {
		final String view = "error/error-500";
		try {
			request.logout();
		} catch (ServletException e) {
			return view;
		}
		return "redirect:/login";
	}
}
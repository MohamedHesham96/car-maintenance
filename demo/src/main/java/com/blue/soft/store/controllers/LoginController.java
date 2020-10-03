package com.blue.soft.store.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	HttpSession httpSession;

	@RequestMapping("/show-login")
	public String showLogin(Model theModle) {

		theModle.addAttribute("user", new User());
		return "login";

	}

	@RequestMapping("/login")
	public String login(@ModelAttribute(name = "user") User theUser, Model theModle) {

		boolean isUser = userService.checkUser(theUser.getName(), theUser.getPassword());

		if (isUser) {

//			httpSession.setAttribute("id", theUser.getId());
//			httpSession.setAttribute("name", theUser.getName());

			theModle.addAttribute("user", theUser);
			return "redirect:/items-list";

		} else {

			return "login";

		}

	}

	@RequestMapping("/logout")
	public String logout(Model theModle) {

		httpSession.removeAttribute("name");
		theModle.addAttribute("user", new User());

		return "login";

	}

}

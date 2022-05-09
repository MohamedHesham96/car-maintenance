package com.blue.soft.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.soft.store.entity.User;
import com.blue.soft.store.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("/users-list")
	public String showUsers(Model theModel) {

		theModel.addAttribute("user", new User());
		theModel.addAttribute("usersList", userService.getAllUsers());
		theModel.addAttribute("users_List", "active");

		return "users-list";
	}

	@RequestMapping("/update-user")
	public String showUsers(@RequestParam(name = "userId") String useriId, Model theModel) {

		theModel.addAttribute("user", userService.getUserById(useriId));
		theModel.addAttribute("usersList", userService.getAllUsers());
		theModel.addAttribute("users_List", "active");

		return "users-list";
	}

	@RequestMapping("/save-user-data")
	public String saveUserData(@ModelAttribute(name = "user") User theUser, Model theModel) {

		if (theUser.getId().equals("")) {

			userService.saveUser(theUser);

		} else {

			User oldUser = userService.getUserById(theUser.getId());

			oldUser.setName(theUser.getName());

			oldUser.setPassword(theUser.getPassword());

			oldUser.setHasMain(theUser.isHasMain());

			oldUser.setHasSales(theUser.isHasSales());

			oldUser.setHasBuys(theUser.isHasBuys());

			oldUser.setHasStore(theUser.isHasStore());

			oldUser.setHasBank(theUser.isHasBank());

			oldUser.setHasItemMove(theUser.isHasItemMove());

			oldUser.setHasClients(theUser.isHasClients());

			oldUser.setHasCompanies(theUser.isHasCompanies());

			oldUser.setHasReports(theUser.isHasReports());

			userService.saveUser(oldUser);

		}

		return "redirect:/users-list";
	}
}

package com.blue.soft.store.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.soft.store.entity.Spend;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.SpendService;

@Controller
public class SpendController {

	@Autowired
	ItemService itemService;

	@Autowired
	SpendService spendService;

	@RequestMapping("/spend-list")
	public String showSpendList(Model theModel) {

		theModel.addAttribute("spendsList", spendService.getAllSpends());

		return "spend-list";
	}

	@RequestMapping("/add-spend")
	public String addSpend(@ModelAttribute("spend") Spend theSpend, Model theModel) {

		theSpend.setDate(LocalDate.now().toString());

		spendService.saveSpend(theSpend);

		theModel.addAttribute("spendsList", spendService.getAllSpends());

		return "redirect:/spend-list";
	}

}

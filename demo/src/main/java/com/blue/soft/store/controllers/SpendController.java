package com.blue.soft.store.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.soft.store.entity.Bank;
import com.blue.soft.store.entity.Spend;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.SpendService;

@Controller
public class SpendController {

	@Autowired
	ItemService itemService;

	@Autowired
	SpendService spendService;

	@Autowired
	BankService bankService;

	@RequestMapping("/spend-list")
	public String showSpendList(Model theModel) {

		theModel.addAttribute("spendsList", spendService.getAllSpends());
		theModel.addAttribute("bank", bankService.getBank());
		return "spend-list";
	}

	@RequestMapping("/add-spend")
	public String addSpend(@ModelAttribute("spend") Spend theSpend, Model theModel) throws Exception {

		if (theSpend.getAmount() > 0) {

			theSpend.setDate(LocalDate.now().toString());

			spendService.saveSpend(theSpend);

			theModel.addAttribute("spendsList", spendService.getAllSpends());

			Bank bank = bankService.getBank();

			bank.setBalance(bank.getBalance() - theSpend.getAmount());
			bank.setBalanceToday(bank.getBalanceToday() - theSpend.getAmount());

			bankService.saveBank(bank);

			return "redirect:/spend-list";

		} else {

			throw new Exception("المبلغ اصغر او اكبر من قيمة الخزنة");
		}
	}

//	@RequestMapping("/update-spend")
//	public String updateSpend(@ModelAttribute("spend") Spend theSpend, Model theModel) throws Exception {
//
//	}

	@RequestMapping("/delete-spend")
	public String deleteSpend(@RequestParam(name = "spendId") String spendId) {

		Spend theSpend = spendService.getSpendById(spendId);

		Bank bank = bankService.getBank();

		bank.setBalance(bank.getBalance() + theSpend.getAmount());
		bank.setBalanceToday(bank.getBalanceToday() + theSpend.getAmount());

		bankService.saveBank(bank);

		spendService.deleteSpendById(spendId);

		return "redirect:/spend-list";

	}

}

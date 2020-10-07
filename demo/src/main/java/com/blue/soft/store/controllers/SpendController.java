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
import com.blue.soft.store.service.BillBuyItemsService;
import com.blue.soft.store.service.BillBuyService;
import com.blue.soft.store.service.BillReturnItemsService;
import com.blue.soft.store.service.BillReturnService;
import com.blue.soft.store.service.BillSellItemsService;
import com.blue.soft.store.service.BillSellService;
import com.blue.soft.store.service.ClientService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.ItemService;
import com.blue.soft.store.service.SpendService;
import com.blue.soft.store.service.UserService;

@Controller
public class SpendController {

	@Autowired
	ItemService itemService;

	@Autowired
	SpendService spendService;

	@Autowired
	ClientService clientSerivce;

	@Autowired
	CompanyService companyService;

	@Autowired
	BillSellService billSellService;

	@Autowired
	BillBuyService billBuyService;

	@Autowired
	BillReturnService billReturnService;

	@Autowired
	ClientService clientService;

	@Autowired
	BillSellItemsService billSellItemsService;

	@Autowired
	BillBuyItemsService billBuyItemsService;

	@Autowired
	BillReturnItemsService billReturnItemsService;

	@Autowired
	BankService bankService;

	@Autowired
	BankController bankController;

	@Autowired
	UserService userService;

	@RequestMapping("/spend-list")
	public String showSpendList(Model theModel) {

		Bank theBank = bankService.getBank();

		if (!theBank.getDate().equals(LocalDate.now().toString())) {

			theBank.setDate(LocalDate.now().toString());
			theBank.setBalanceToday(0);
			bankService.saveBank(theBank);
		}

		theModel.addAttribute("spendsList", spendService.getAllSpends());
		theModel.addAttribute("bank", bankService.getBank());
		theModel.addAttribute("spendTotal", spendService.getSpendTotal());
		theModel.addAttribute("spendTotalToday", spendService.getSpendTotalToday());

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

	@RequestMapping("/show-today-report")
	public String showTodayReport(Model theModel) {

		Bank theBank = bankService.getBank();

		if (!theBank.getDate().equals(LocalDate.now().toString())) {

			theBank.setDate(LocalDate.now().toString());
			theBank.setBalanceToday(0);
			bankService.saveBank(theBank);
		}

		theModel.addAttribute("bank", theBank);
		theModel.addAttribute("spendTotalToday", spendService.getSpendTotalToday());
		theModel.addAttribute("clientDraweeTotal", clientSerivce.getDraweeTotal());
		theModel.addAttribute("companyDraweeTotal", companyService.getDraweeTotal());
		theModel.addAttribute("totalSalesToday", billSellItemsService.getTotalSalesToday());
		theModel.addAttribute("totalReturnsToday", billReturnItemsService.getTotalReturnsToday());
		theModel.addAttribute("totalPayedSalesToday", billSellItemsService.getTotalPayedSalesToday());
		theModel.addAttribute("totalLateSalesToday", billSellItemsService.getTotalLateSalesToday());

		theModel.addAttribute("totalBuysToday", billBuyItemsService.getTotalBuysToday());
		theModel.addAttribute("totalGain", billSellItemsService.getTotalGains());
		theModel.addAttribute("sellBillCountToday", billSellService.getSellBillCountToday());
		theModel.addAttribute("payedSellBillCountToday", billSellService.getPayedSellBillCountToday());
		theModel.addAttribute("lateSellBillCountToday", billSellService.getLateSellBillCountToday());
		theModel.addAttribute("buyBillCountToday", billBuyService.getBuyBillCountToday());
		theModel.addAttribute("returnBillCountToday", billReturnService.getReturnBillCountToday());

		return "today-report";

	}

}

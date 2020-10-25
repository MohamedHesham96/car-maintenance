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
import com.blue.soft.store.service.CompanyBillReturnItemsService;
import com.blue.soft.store.service.CompanyBillReturnService;
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
	CompanyBillReturnService companyBillReturnService;

	@Autowired
	CompanyBillReturnItemsService companyBillReturnItemsService;

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
			theBank.setBalance(0);
			bankService.saveBank(theBank);
		}

		theModel.addAttribute("theBank", bankService.getBank());

		theModel.addAttribute("spendsList", spendService.getAllSpends());

		theModel.addAttribute("spendTotal", spendService.getSpendTotal());

		theModel.addAttribute("spendTotalByDate",
				spendService.getSpendTotalByDate(LocalDate.now().toString(), LocalDate.now().toString()));

		theModel.addAttribute("bank", "active");

		return "spend-list";
	}

	@RequestMapping("/balances-list")
	public String showBalancesList(Model theModel) {

		theModel.addAttribute("theBank", bankService.getBank());
		theModel.addAttribute("balancesList", spendService.getAllBalancess());

		theModel.addAttribute("balancesTotal", spendService.getBalancesTotal());

		theModel.addAttribute("balancesTotalByDate",
				spendService.getBalancesTotalByDate(LocalDate.now().toString(), LocalDate.now().toString()));

		theModel.addAttribute("bank", "active");

		return "balances-list";
	}

	@RequestMapping("/add-spend")
	public String addSpend(@ModelAttribute("spend") Spend theSpend, Model theModel) throws Exception {

		Bank bank = bankService.getBank();

		if (theSpend.getAmount() > 0 && theSpend.getAmount() < bank.getBalance()) {

			theSpend.setDate(LocalDate.now().toString());

			theSpend.setType("مصروف");

		} else {

			throw new Exception("المبلغ  اكبر من قيمة الخزنة او اقل من صفر");
		}

		spendService.saveSpend(theSpend);

		bank.setBalance(bank.getBalance() - theSpend.getAmount());
		bank.setBalanceToday(bank.getBalance() - theSpend.getAmount());

		bankService.saveBank(bank);

		return "redirect:/spend-list";

	}

	@RequestMapping("/add-balance")
	public String addBalance(@ModelAttribute("spend") Spend theBalance, Model theModel) throws Exception {

		Bank bank = bankService.getBank();

		if (theBalance.getAmount() > 0) {

			theBalance.setDate(LocalDate.now().toString());

			theBalance.setType("رصيد");

		} else {

			throw new Exception("المبلغ  اقل من صفر");
		}

		spendService.saveSpend(theBalance);

		bank.setBalance(bank.getBalance() + theBalance.getAmount());
		bank.setBalanceToday(bank.getBalance() + theBalance.getAmount());

		bankService.saveBank(bank);

		return "redirect:/balances-list";

	}

//	@RequestMapping("/update-spend")
//	public String updateSpend(@ModelAttribute("spend") Spend theSpend, Model theModel) throws Exception {
//
//	}

	@RequestMapping("/delete-record")
	public String deleteSpend(@RequestParam(name = "spendId") String spendId) {

		Spend theSpend = spendService.getSpendById(spendId);

		Bank bank = bankService.getBank();

		int flagVal = theSpend.getType().equals("مصروف") ? 1 : -1;

		bank.setBalance(bank.getBalance() + theSpend.getAmount() * flagVal);
		bank.setBalanceToday(bank.getBalance() + theSpend.getAmount() * flagVal);

		bankService.saveBank(bank);

		spendService.deleteSpendById(spendId);

		if (flagVal == 1)
			return "redirect:/spend-list";

		else
			return "redirect:/balances-list";

	}

	@RequestMapping("/update-record")
	public String updateSpend(@ModelAttribute("spend") Spend theRecord) {

		Spend theOldRecord = spendService.getSpendById(theRecord.getId());

		Bank bank = bankService.getBank();

		int flagVal = theOldRecord.getType().equals("مصروف") ? 1 : -1;

		bank.setBalance(bank.getBalance() + theRecord.getAmount() * flagVal);

//		if (theOldRecord.getDate().equals(LocalDate.now().toString())) {
//
//			bank.setBalanceToday(bank.getBalanceToday() + theOldRecord.getAmount() * flagVal);
//			bank.setBalanceToday(bank.getBalanceToday() - theRecord.getAmount() * flagVal);
//
//		}
//
//		bank.setBalance(bank.getBalance() + theRecord.getAmount() * flagVal);

		theOldRecord.setAmount(theRecord.getAmount());
		theOldRecord.setNote(theRecord.getNote());

		spendService.saveSpend(theOldRecord);
		bankService.saveBank(bank);

		if (flagVal == 1)
			return "redirect:/spend-list";

		else
			return "redirect:/balances-list";

	}

	@RequestMapping("/show-report")
	public String showReport(@RequestParam(name = "date", defaultValue = "") String date,
			@RequestParam(name = "dateFrom", defaultValue = "") String dateFrom,
			@RequestParam(name = "dateTo", defaultValue = "") String dateTo,
			@RequestParam(name = "action", defaultValue = "") String action, Model theModel) {

		theModel.addAttribute("dateFrom", dateFrom);
		theModel.addAttribute("dateTo", dateTo);

		Bank theBank = bankService.getBank();

		if (!theBank.getDate().equals(LocalDate.now().toString())) {

			theBank.setDate(LocalDate.now().toString());
			theBank.setBalance(0);
			bankService.saveBank(theBank);
		}

		if (action.equals("date")) {

			if (date.equals("")) {

				dateFrom = LocalDate.now().toString();
				dateTo = LocalDate.now().toString();

			} else {

				dateFrom = date;
				dateTo = date;
			}

		} else if (action.equals("dateFromDateTo")) {

			if (dateFrom.equals(""))
				dateFrom = "2020-01-01";

			if (dateTo.equals(""))
				dateTo = LocalDate.now().toString();
		} else {

			dateFrom = LocalDate.now().toString();
			dateTo = LocalDate.now().toString();

		}

		theModel.addAttribute("bank", theBank);

		theModel.addAttribute("spendTotal", spendService.getSpendTotalByDate(dateFrom, dateTo));

		theModel.addAttribute("clientDraweeTotal", clientSerivce.getDraweeTotal());
		theModel.addAttribute("companyDraweeTotal", companyService.getDraweeTotal());

		theModel.addAttribute("totalSales", billSellItemsService.getTotalSalesByDate(dateFrom, dateTo));

		theModel.addAttribute("totalPayedSales", billSellItemsService.getTotalPayedSalesByDate(dateFrom, dateTo));

		theModel.addAttribute("totalLateSales", billSellItemsService.getTotalLateSalesByDate(dateFrom, dateTo));

		theModel.addAttribute("totalReturns", billReturnItemsService.getTotalClientsReturnsByDate(dateFrom, dateTo));

		theModel.addAttribute("totalCompaniesReturns",
				companyBillReturnItemsService.getTotalCompaniesReturnsByDate(dateFrom, dateTo));

		theModel.addAttribute("totalBuys", billBuyItemsService.getTotalBuysByDate(dateFrom, dateTo));

		theModel.addAttribute("totalGain", billSellItemsService.getTotalGainsByDate(dateFrom, dateTo));

		theModel.addAttribute("sellBillCount", billSellService.getSellBillCountByDate(dateFrom, dateTo));

		theModel.addAttribute("payedSellBillCount", billSellService.getPayedSellBillCountByDate(dateFrom, dateTo));

		theModel.addAttribute("lateSellBillCount", billSellService.getLateSellBillCountByDate(dateFrom, dateTo));

		theModel.addAttribute("buyBillCount", billBuyService.getBuyBillCountByDate(dateFrom, dateTo));

		theModel.addAttribute("returnBillCount", billReturnService.getReturnBillCountByDate(dateFrom, dateTo));
		theModel.addAttribute("companyReturnBillCount",
				companyBillReturnService.getCompanyBillReturnCountByDate(dateFrom, dateTo));

		theModel.addAttribute("today_report", "active");
		theModel.addAttribute("date", date);

		return "report";

	}

	@RequestMapping("/bank-menu")
	public String showBankMenu(Model theModel) {
		theModel.addAttribute("bank", "active");

		return "bank-menu";
	}

}

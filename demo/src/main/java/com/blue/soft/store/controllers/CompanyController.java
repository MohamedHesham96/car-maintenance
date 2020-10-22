package com.blue.soft.store.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blue.soft.store.entity.Company;
import com.blue.soft.store.entity.Pay;
import com.blue.soft.store.service.BankService;
import com.blue.soft.store.service.CompanyService;
import com.blue.soft.store.service.PayService;

@Controller
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@Autowired
	PayService payService;

	@Autowired
	BankService bankService;

	@Autowired
	BankController bankController;

	@RequestMapping("/companies-list")
	public String showCompanies(Model theModel) {

		theModel.addAttribute("company", new Company());
		theModel.addAttribute("companiesList", companyService.getAllCompanies());
		theModel.addAttribute("companies_list", "active");

		return "companies-list";
	}

	@RequestMapping("/add-new-company")
	public String addNewCompany(@ModelAttribute(name = "company") Company theCompany, Model theModel) {

		companyService.addNewCompany(theCompany);

		return "redirect:/companies-list";
	}

	@RequestMapping("/delete-company-pay")
	public String deleteCompanyPay(@RequestParam(name = "companyId") String companyId,
			@RequestParam(name = "payId") String payId, Model theModel) throws Exception {

		Company company = companyService.getCompanyById(companyId);

		Pay thePay = payService.getPayById(payId);

		company.setDrawee(company.getDrawee() + thePay.getAmount());

		bankController.updateBankBalance("add", thePay.getAmount());

		payService.deletePayById(payId);

		theModel.addAttribute("company", company);
		theModel.addAttribute("companies_list", "active");

		return "company-pay";
	}

	@RequestMapping("/search-for-company")
	public String searchForCompany(@ModelAttribute(name = "companyName") String theCompanyName, Model theModel) {

		theModel.addAttribute("company", new Company());

		theModel.addAttribute("companiesList", companyService.searchForCompany(theCompanyName));
		theModel.addAttribute("companies_list", "active");

		return "companies-list";
	}

	@RequestMapping("/show-pays-company")
	public String showCompanyPays(@RequestParam(name = "companyId") String companyId, Model theModel) {

		theModel.addAttribute("company", companyService.getCompanyById(companyId));
		theModel.addAttribute("companies_list", "active");

		return "company-pay";
	}

	@RequestMapping("/add-company-pay")
	public String addCompanyPay(@RequestParam(name = "companyId") String companyId,
			@RequestParam(name = "amount") float amount, Model theModel) throws Exception {

		Company company = companyService.getCompanyById(companyId);

		if (amount > company.getDrawee())
			throw new Exception("مبلغ الدفع اكبر من الدين");

		Pay pay = new Pay();

		pay.setBalanceNow(company.getDrawee() - amount);

		pay.setAmount(amount);
		pay.setDate(LocalDate.now().toString());
		pay.setCompany(company);

		company.addPay(pay);
		company.setDrawee(company.getDrawee() - amount);
		companyService.addNewCompany(company);

		// update the Bank
		bankController.updateBankBalance("less", pay.getAmount());

		theModel.addAttribute("company", company);
		theModel.addAttribute("companies_list", "active");

		return "company-pay";
	}

	@RequestMapping("/show-company-bills-list")
	public String showCompanyBillsList(@RequestParam(name = "companyId") String companyId, Model theModel) {

		theModel.addAttribute("company", companyService.getCompanyById(companyId));
		theModel.addAttribute("companies_list", "active");

		return "company-buy-bill-list";
	}

}

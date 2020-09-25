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
import com.blue.soft.store.service.CompanyService;

@Controller
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@RequestMapping("/companies-list")
	public String showCompanies(Model theModel) {

		theModel.addAttribute("company", new Company());
		theModel.addAttribute("companiesList", companyService.getAllCompanies());

		return "companies-list";
	}

	@RequestMapping("/add-new-company")
	public String addNewCompany(@ModelAttribute(name = "company") Company theCompany, Model theModel) {

		companyService.addNewCompany(theCompany);

		return "redirect:/companies-list";
	}

	@RequestMapping("/search-for-company")
	public String searchForCompany(@ModelAttribute(name = "companyName") String theCompanyName, Model theModel) {

		theModel.addAttribute("company", new Company());
		System.out.println(companyService.searchForCompany(theCompanyName).get(0).getName());
		theModel.addAttribute("companiesList", companyService.searchForCompany(theCompanyName));

		return "companies-list";
	}

	@RequestMapping("/show-pays-company")
	public String showCompanyPays(@RequestParam(name = "companyId") String companyId, Model theModel) {

		theModel.addAttribute("company", companyService.getCompanyById(companyId));

		return "company-pay";
	}

	@RequestMapping("/add-company-pay")
	public String addCompanyPay(@RequestParam(name = "companyId") String companyId,
			@RequestParam(name = "amount") float amount, Model theModel) throws Exception {

		Company company = companyService.getCompanyById(companyId);

		if (amount > company.getDrawee())
			throw new Exception("مبلغ الدفع اكبر من الدين");

		Pay pay = new Pay();
		pay.setAmount(amount);
		pay.setDate(LocalDate.now().toString());
		pay.setCompany(company);
		pay.setAmount(amount);
		company.addPay(pay);
		company.setDrawee(company.getDrawee() - amount);
		companyService.addNewCompany(company);

		theModel.addAttribute("company", company);

		return "company-pay";
	}

}

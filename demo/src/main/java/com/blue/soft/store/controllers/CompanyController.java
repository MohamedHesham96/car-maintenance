package com.blue.soft.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blue.soft.store.entity.Company;
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
}

package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.CompanyRepository;
import com.blue.soft.store.entity.Company;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	public List<Company> getAllCompanies() {

		return (List<Company>) companyRepository.findAll();
	}

	public void addNewCompany(Company theCompany) {

		companyRepository.save(theCompany);

	}

	public List<Company> searchForCompany(String theCompanyName) {

		return (List<Company>) companyRepository.findByNameContaining(theCompanyName);

	}

	public Company getCompanyById(String id) {

		return companyRepository.findById(id).get();
	}

	public Float getDraweeTotal() {

		Float draweeTotal = companyRepository.getDraweeTotal();

		return draweeTotal != null ? draweeTotal : 0;
	}

}

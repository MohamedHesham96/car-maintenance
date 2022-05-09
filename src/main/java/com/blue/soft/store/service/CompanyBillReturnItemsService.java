package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.CompanyBillReturnItemsRepository;
import com.blue.soft.store.entity.CompanyBillReturnItem;

@Service
public class CompanyBillReturnItemsService {

	@Autowired
	CompanyBillReturnItemsRepository companyBillReturnItemsRepository;

	public void addCompanyBillReturnItem(CompanyBillReturnItem companyBillReturnItem) {

		companyBillReturnItemsRepository.save(companyBillReturnItem);

	}

	public void deleteCompanyBillReturnItem(String companyBillReturnItemId) {

		companyBillReturnItemsRepository.deleteById(companyBillReturnItemId);

	}

	public CompanyBillReturnItem getCompanyBillReturnItem(String companyBillReturnItemId) {

		return companyBillReturnItemsRepository.findById(companyBillReturnItemId).get();

	}

	public Float getTotalCompaniesReturnsByDate(String dateFrom, String dateTo) {

		Float totalReturns = companyBillReturnItemsRepository.getTotalCompaniesReturnsByDate(dateFrom, dateTo);

		return totalReturns != null ? totalReturns : 0;
	}

}

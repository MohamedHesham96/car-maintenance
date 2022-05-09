package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.CompanyBillReturnRepository;
import com.blue.soft.store.entity.CompanyBillReturn;

@Service
public class CompanyBillReturnService {

	@Autowired
	CompanyBillReturnRepository companyBillReturnRepository;

	public void saveCompanyReturnBill(CompanyBillReturn CompanyBillReturn) {

		companyBillReturnRepository.save(CompanyBillReturn);

	}

	public CompanyBillReturn getCompanyBillReturnById(String id) {

		return (CompanyBillReturn) companyBillReturnRepository.findById(id).get();
	}

	public CompanyBillReturn getLast() {

		return companyBillReturnRepository.findTopByOrderByIdDesc();
	}

	public void deleteCompanyReturnBill(String id) {

		companyBillReturnRepository.deleteById(id);
	}

	public List<CompanyBillReturn> getAllCompanyReturnBills() {

		return (List<CompanyBillReturn>) companyBillReturnRepository.findAllByOrderByIdDesc();
	}

	public List<CompanyBillReturn> getCompanyBillReturnContainingId(String id) {

		return (List<CompanyBillReturn>) companyBillReturnRepository.findByIdContainingOrderByIdDesc(id);
	}

	public Integer getCompanyBillReturnCountByDate(String dateFrom, String dateTo) {

		Integer crbCountToday = companyBillReturnRepository.getCompanyBillReturnCountByDate(dateFrom, dateTo);

		return crbCountToday != null ? crbCountToday : 0;

	}

	public CompanyBillReturn getCompanyBillReturnByUpdaterId(String updaterId) {

		return companyBillReturnRepository.getCompanyBillReturnByUpdaterId(updaterId);
	}

	public CompanyBillReturn getCompanyBillReturnBySaverId(String saverId) {

		return companyBillReturnRepository.getCompanyBillReturnBySaverId(saverId);
	}
}

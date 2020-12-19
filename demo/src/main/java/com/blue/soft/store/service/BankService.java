package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BankRepository;
import com.blue.soft.store.entity.Bank;

@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;

	public Bank getBankToDay() {

		return bankRepository.getBankToDay();
	}

	public Bank getLast() {

		return bankRepository.findTopByOrderByIdDesc();
	}

	public void saveBank(Bank theBank) {

		bankRepository.save(theBank);

	}

	public Float getBankBalanceTodayByDate(String dateFrom, String dateTo) {

		Float bankBalanceToday = bankRepository.getBankBalanceTodayByDate(dateFrom, dateTo);

		return bankBalanceToday != null ? bankBalanceToday : 0;

	}

	public Float getBankBalanceByDate(String dateFrom, String dateTo) {

		Float bankBalance = bankRepository.getBankBalanceByDate(dateFrom, dateTo);

		return bankBalance != null ? bankBalance : 0;

	}

}

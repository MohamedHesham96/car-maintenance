package com.blue.soft.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blue.soft.store.entity.Bank;
import com.blue.soft.store.service.BankService;

@Component
public class BankController {

	@Autowired
	BankService bankService;

	public void updateBankBalance(String operationType, float theAmount) {

		Bank bank = bankService.getBank();

		if (operationType == "add") {

			bank.setBalance(bank.getBalance() + theAmount);
			bank.setBalanceToday(bank.getBalanceToday() + theAmount);

		} else if (operationType == "less") {

			bank.setBalance(bank.getBalance() - theAmount);
			bank.setBalanceToday(bank.getBalanceToday() - theAmount);

		}

		bankService.saveBank(bank);

	}
}

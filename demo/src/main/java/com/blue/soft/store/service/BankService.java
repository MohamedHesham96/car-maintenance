package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BankRepository;
import com.blue.soft.store.entity.Bank;

@Service
public class BankService {

	@Autowired
	BankRepository bankRepository;

	public Bank getBank() {

		return bankRepository.findById("1").get();
	}

	public void saveBank(Bank theBank) {

		bankRepository.save(theBank);

	}
}

package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BankRepository;
import com.blue.soft.store.entity.Bank;

import java.time.LocalDate;

@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public Bank getBankToDay() {
        Bank theBank = bankRepository.getBankToDay();
        if (theBank != null && !theBank.getDate().equals(LocalDate.now().toString())) {
            theBank.setDate(LocalDate.now().toString());
            theBank.setBalance(0);
        } else {
            theBank = new Bank(0.0f, 0.0f, LocalDate.now().toString());
        }
        return bankRepository.save(theBank);
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

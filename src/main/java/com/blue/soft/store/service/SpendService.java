package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.SpendRepository;
import com.blue.soft.store.entity.Spend;

@Service
public class SpendService {

	@Autowired
	SpendRepository spendRepository;

	float spendTotal;

	public List<Spend> getAllSpends() {

		return (List<Spend>) spendRepository.findByTypeOrderByIdDesc("مصروف");
	}

	public List<Spend> getAllBalancess() {

		return (List<Spend>) spendRepository.findByTypeOrderByIdDesc("رصيد");
	}

	public void saveSpend(Spend theSpend) {

		spendRepository.save(theSpend);

	}

	public Spend getSpendById(String id) {

		return spendRepository.findById(id).get();
	}

	public void deleteSpendById(String id) {

		spendRepository.deleteById(id);
	}

	public Float getSpendTotal() {

		Float spendTotal = spendRepository.getSpendTotal();

		return spendTotal != null ? spendTotal : 0;
	}

	public Float getSpendTotalByDate(String dateFrom, String dateTo) {

		Float spendTotalToday = spendRepository.getSpendTotalByDate(dateFrom, dateTo);

		return spendTotalToday != null ? spendTotalToday : 0;
	}

	public Float getBalancesTotal() {

		Float balancesTotal = spendRepository.getBalancesTotal();

		return balancesTotal != null ? balancesTotal : 0;
	}

	public Float getBalancesTotalByDate(String dateFrom, String dateTo) {

		Float balancesTotalToday = spendRepository.getBalancesTotalByDate(dateFrom, dateTo);

		return balancesTotalToday != null ? balancesTotalToday : 0;
	}

}

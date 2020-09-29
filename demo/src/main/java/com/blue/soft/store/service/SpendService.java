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

	public List<Spend> getAllSpends() {

		return (List<Spend>) spendRepository.findAllByOrderByIdDesc();
	}

	public void saveSpend(Spend theSpend) {

		spendRepository.save(theSpend);

	}

//	public List<Spend> searchForSpend(String theSpendName) {
//
//		return (List<Spend>) spendRepository.findByNameContaining(theSpendName);
//
//	}

	public Spend getSpendById(String id) {

		return spendRepository.findById(id).get();
	}

	public void deleteSpendById(String id) {

		spendRepository.deleteById(id);
	}

}

package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BillSellRepository;
import com.blue.soft.store.entity.BillSell;

@Service
public class BillSellService {

	@Autowired
	BillSellRepository billSellRepository;

	public void addNewSellBill(BillSell billSell) {

		billSellRepository.save(billSell);

	}

	public BillSell getBillSellById(String id) {

		return (BillSell) billSellRepository.findById(id).get();
	}

	public BillSell getLast() {

		return billSellRepository.findTopByOrderByIdDesc();
	}

	public void deleteSellBill(String id) {

		billSellRepository.deleteById(id);
	}

	public List<BillSell> getAllSellBills() {

		return (List<BillSell>) billSellRepository.findAll();
	}

	public List<BillSell> getBillSellContainingId(String id) {

		return (List<BillSell>) billSellRepository.findByIdContaining(id);
	}

}

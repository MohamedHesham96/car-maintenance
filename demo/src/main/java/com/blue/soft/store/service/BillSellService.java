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

	public void saveSellBill(BillSell billSell) {

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

		return (List<BillSell>) billSellRepository.findAllBySaverIdIsNullOrderByIdDesc();
	}

	public List<BillSell> getBillSellContainingId(String id) {

		return (List<BillSell>) billSellRepository.findByIdContainingOrderByIdDesc(id);
	}

	public Integer getSellBillCountToday() {

		Integer sbCountToday = billSellRepository.getSellBillCountToday();

		return sbCountToday != null ? sbCountToday : 0;

	}

	public Integer getLateSellBillCountToday() {

		Integer lsbCountToday = billSellRepository.getLateSellBillCountToday();

		return lsbCountToday != null ? lsbCountToday : 0;

	}

	public Object getPayedSellBillCountToday() {

		Integer lsbCountToday = billSellRepository.getPayedSellBillCountToday();

		return lsbCountToday != null ? lsbCountToday : 0;
	}

	public BillSell getBillSellByUpdaterId(String updateId) {

		return billSellRepository.getBillSellByUpdaterId(updateId);
	}

	public BillSell getBillSellBySaverId(String userId) {

		return billSellRepository.getBillSellBySaverId(userId);
	}

}

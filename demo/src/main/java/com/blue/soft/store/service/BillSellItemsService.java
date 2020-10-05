package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BillSellItemsRepository;
import com.blue.soft.store.entity.BillSellItem;

@Service
public class BillSellItemsService {

	@Autowired
	BillSellItemsRepository billSellItemsRepository;

	public void addBillSellItem(BillSellItem billSellItem) {

		billSellItemsRepository.save(billSellItem);

	}

	public void deleteBillSellItem(String billSellItemId) {

		billSellItemsRepository.deleteById(billSellItemId);

	}

	public BillSellItem getBillSellItem(String billSellItemId) {

		return billSellItemsRepository.findById(billSellItemId).get();

	}

	public float getTotalSallsToday() {

		Float totalSalls = billSellItemsRepository.getTotalSallsToday();

		return totalSalls != null ? totalSalls : 0;

	}

	public Float getTotalGains() {

		Float totalGains = billSellItemsRepository.getTotalGains();

		return totalGains != null ? totalGains : 0;

	}

}

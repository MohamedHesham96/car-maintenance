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

	public float getTotalSalesByDate(String dateFrom, String dateTo) {

		Float totalSalls = billSellItemsRepository.getTotalSalesByDate(dateFrom, dateTo);

		return totalSalls != null ? totalSalls : 0;

	}

	public Float getTotalGainsByDate(String dateFrom, String dateTo) {

		Float totalGains = billSellItemsRepository.getTotalGainsByDate(dateFrom, dateTo);

		return totalGains != null ? totalGains : 0;

	}

	public Float getTotalPayedSalesByDate(String dateFrom, String dateTo) {

		Float totalPayedSalls = billSellItemsRepository.getTotalPayedSalesByDate(dateFrom, dateTo);

		return totalPayedSalls != null ? totalPayedSalls : 0;
	}

	public Float getTotalLateSalesByDate(String dateFrom, String dateTo) {

		Float totalLateSalls = billSellItemsRepository.getTotalLateSalesByDate(dateFrom, dateTo);

		return totalLateSalls != null ? totalLateSalls : 0;
	}

	public Float getTotalSalesBetweenDates(String dateFrom, String dateTo) {

		Float totalLateSalls = billSellItemsRepository.getTotalLateSalesByDate(dateFrom, dateTo);

		return totalLateSalls != null ? totalLateSalls : 0;
	}

}

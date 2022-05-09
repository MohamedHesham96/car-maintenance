package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BillReturnItemsRepository;
import com.blue.soft.store.entity.BillReturnItem;

@Service
public class BillReturnItemsService {

	@Autowired
	BillReturnItemsRepository billReturnItemsRepository;

	public void addBillReturnItem(BillReturnItem billReturnItem) {

		billReturnItemsRepository.save(billReturnItem);

	}

	public void deleteBillReturnItem(String billReturnItemId) {

		billReturnItemsRepository.deleteById(billReturnItemId);

	}

	public BillReturnItem getBillReturnItem(String billReturnItemId) {

		return billReturnItemsRepository.findById(billReturnItemId).get();

	}

	public Float getTotalClientsReturnsByDate(String dateFrom, String dateTo) {

		Float totalLateSalls = billReturnItemsRepository.getTotalClientsReturnsByDate(dateFrom, dateTo);

		return totalLateSalls != null ? totalLateSalls : 0;
	}

}

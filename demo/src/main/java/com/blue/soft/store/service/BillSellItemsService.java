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

}

package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BillBuyItemsRepository;
import com.blue.soft.store.DAO.BillReturnItemsRepository;
import com.blue.soft.store.DAO.BillSellItemsRepository;
import com.blue.soft.store.DAO.BillSellRepository;
import com.blue.soft.store.DAO.TempBillItemsRepository;
import com.blue.soft.store.entity.BillSellItem;
import com.blue.soft.store.entity.Item;
import com.blue.soft.store.entity.TempBillItem;

@Service
public class TempBillItemsService {

	@Autowired
	TempBillItemsRepository tempBillItemsRepository;

	@Autowired
	BillSellItemsRepository billSellItemsRepository;

	@Autowired
	BillBuyItemsRepository billBuyItemsRepository;

	@Autowired
	BillReturnItemsRepository billReturnItemsRepository;

	public void addBillSellItems(List<BillSellItem> billSellItemsList) {

		for (BillSellItem bsItem : billSellItemsList) {

			Item theItem = bsItem.getItem();

			tempBillItemsRepository.save(new TempBillItem(theItem.getId(), bsItem.getBillSell().getId(), "sellBill",
					bsItem.getQuantity(), bsItem.getSellPrice()));

		}

	}

	public List<TempBillItem> getTempBillSellItems(String billId) {

		return tempBillItemsRepository.findByBillIdAndBillType(billId, "sellBill");

	}

	public void addBillBuyItems(BillSellItem billSellItem) {

		billSellItemsRepository.save(billSellItem);

	}

	public void addBillReturnItems(BillSellItem billSellItem) {

		billSellItemsRepository.save(billSellItem);

	}

	public void deleteBillSellItem(String tempBillSellItemId) {

		tempBillItemsRepository.deleteById(tempBillSellItemId);

	}

	public BillSellItem getBillSellItem(String billSellItemId) {

		return billSellItemsRepository.findById(billSellItemId).get();

	}

}

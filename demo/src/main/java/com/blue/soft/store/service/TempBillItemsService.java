package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BillBuyItemsRepository;
import com.blue.soft.store.DAO.BillReturnItemsRepository;
import com.blue.soft.store.DAO.BillSellItemsRepository;
import com.blue.soft.store.DAO.TempBillItemsRepository;
import com.blue.soft.store.entity.BillBuyItem;
import com.blue.soft.store.entity.BillReturnItem;
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

			TempBillItem tempbillItem = new TempBillItem(theItem.getId(), bsItem.getBillSell().getId(), "sellBill",
					bsItem.getQuantity(), bsItem.getSellPrice());

			tempbillItem.setBuyPrice(theItem.getBuyPrice());

			tempBillItemsRepository.save(tempbillItem);

		}

	}

	public void addBillBuyItems(List<BillBuyItem> billBuyItemsList) {

		for (BillBuyItem bbItem : billBuyItemsList) {

			Item theItem = bbItem.getItem();

			tempBillItemsRepository.save(new TempBillItem(theItem.getId(), bbItem.getBillBuy().getId(), "buyBill",
					bbItem.getQuantity(), bbItem.getBuyPrice()));

		}

	}

	public void addBillReturnItems(List<BillReturnItem> billReturnItemsList) {

		for (BillReturnItem brItem : billReturnItemsList) {

			Item theItem = brItem.getItem();

			tempBillItemsRepository.save(new TempBillItem(theItem.getId(), brItem.getBillReturn().getId(), "returnBill",
					brItem.getQuantity(), brItem.getReturnPrice()));

		}

	}

	public List<TempBillItem> getTempBillItems(String billId, String billType) {

		if (billType.equals("sellBill"))
			return tempBillItemsRepository.findByBillIdAndBillType(billId, "sellBill");

		else if (billType.equals("buyBill"))
			return tempBillItemsRepository.findByBillIdAndBillType(billId, "buyBill");

		else
			return tempBillItemsRepository.findByBillIdAndBillType(billId, "returnBill");

	}

	public void addBillReturnItems(BillSellItem billSellItem) {

		billSellItemsRepository.save(billSellItem);

	}

	public void deleteTempBillItems(String tempBillSellItemId) {

		tempBillItemsRepository.deleteById(tempBillSellItemId);

//		tempBillItemsRepository.deleteByBillIdAndBillType(billSellId, "sellBill");

	}

	public BillSellItem getBillSellItem(String billSellItemId) {

		return billSellItemsRepository.findById(billSellItemId).get();

	}

}

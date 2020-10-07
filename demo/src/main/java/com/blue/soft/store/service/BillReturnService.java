package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.BillReturnRepository;
import com.blue.soft.store.entity.BillReturn;

@Service
public class BillReturnService {

	@Autowired
	BillReturnRepository billReturnRepository;

	public void saveReturnBill(BillReturn billReturn) {

		billReturnRepository.save(billReturn);

	}

	public BillReturn getBillReturnById(String id) {

		return (BillReturn) billReturnRepository.findById(id).get();
	}

	public BillReturn getLast() {

		return billReturnRepository.findTopByOrderByIdDesc();
	}

	public void deleteReturnBill(String id) {

		billReturnRepository.deleteById(id);
	}

	public List<BillReturn> getAllReturnBills() {

		return (List<BillReturn>) billReturnRepository.findAllByOrderByIdDesc();
	}

	public List<BillReturn> getBillReturnContainingId(String id) {

		return (List<BillReturn>) billReturnRepository.findByIdContainingOrderByIdDesc(id);
	}

	public Integer getReturnBillCountToday() {

		Integer rbCountToday = billReturnRepository.getReturnBillCountToday();

		return rbCountToday != null ? rbCountToday : 0;

	}

	public BillReturn getBillReturnByUpdaterId(String updateId) {

		return billReturnRepository.getBillReturnByUpdaterId(updateId);
	}

	public BillReturn getBillReturnBySaverId(String userId) {

		return billReturnRepository.getBillReturnBySaverId(userId);
	}
}

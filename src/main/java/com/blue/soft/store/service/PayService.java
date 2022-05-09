package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.PayRepository;
import com.blue.soft.store.entity.Pay;

@Service
public class PayService {

	@Autowired
	PayRepository payRepository;

	public void deletePayById(String id) {

		payRepository.deleteById(id);
	}

	public Pay getPayById(String payId) {

		return payRepository.findById(payId).get();
	}

}

package com.blue.soft.store.DAO;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSell;

public interface BillSellRepository extends CrudRepository<BillSell, String> {

	public BillSell findTopByOrderByIdDesc();

}
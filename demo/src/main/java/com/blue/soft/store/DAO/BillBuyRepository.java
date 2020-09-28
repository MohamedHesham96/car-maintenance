package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillBuy;

public interface BillBuyRepository extends CrudRepository<BillBuy, String> {

	public BillBuy findTopByOrderByIdDesc();

	public List<BillBuy> findByIdContainingOrderByIdDesc(String billId);

	public List<BillBuy> findAllByOrderByIdDesc();

	public BillBuy findByUpdateNowTrue();

}

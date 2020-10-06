package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillBuy;

public interface BillBuyRepository extends CrudRepository<BillBuy, String> {

	public BillBuy findTopByOrderByIdDesc();

	public List<BillBuy> findByIdContainingOrderByIdDesc(String billId);

	public List<BillBuy> findAllBySaverIdIsNullOrderByIdDesc();

	@Query("SELECT COUNT(bb) FROM BillBuy bb where date = CURRENT_DATE  and saver = null")
	public Integer getBuyBillCountToday();

	public BillBuy getBillBuyByUpdaterId(String updaterId);

	public BillBuy getBillBuyBySaverId(String saverId);
}

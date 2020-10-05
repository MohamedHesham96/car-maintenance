package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSell;

public interface BillSellRepository extends CrudRepository<BillSell, String> {

	public BillSell findTopByOrderByIdDesc();

	public List<BillSell> findByIdContainingOrderByIdDesc(String billId);

	public List<BillSell> findAllByOrderByIdDesc();

	public BillSell findByUpdateNowTrue();
//	(bs.id)

	@Query("SELECT COUNT(bs) FROM BillSell bs where date = CURRENT_DATE")
	public Integer getSellBillCountToday();

	@Query("SELECT COUNT(bs) FROM BillSell bs where date = CURRENT_DATE and late = 1")
	public Integer getLateSellBillCountToday();

	@Query("SELECT COUNT(bs) FROM BillSell bs where date = CURRENT_DATE and late = 0")
	public Integer getPayedSellBillCountToday();

}

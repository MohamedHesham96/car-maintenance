package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSell;

public interface BillSellRepository extends CrudRepository<BillSell, String> {

	public BillSell findTopByOrderByIdDesc();

	public List<BillSell> findByIdContainingOrderByIdDesc(String billId);

	public List<BillSell> findAllBySaverIdIsNullOrderByIdDesc();

//	(bs.id)

	@Query("SELECT COUNT(bs) FROM BillSell bs where date between ?1 and ?2 and saver = null")
	public Integer getSellBillCountByDate(String dateFrom, String dateTo);

	@Query("SELECT COUNT(bs) FROM BillSell bs where date between ?1 and ?2 and late = 1 and saver = null")
	public Integer getLateSellBillCountByDate(String dateFrom, String dateTo);

	@Query("SELECT COUNT(bs) FROM BillSell bs where date between ?1 and ?2 and late = 0 and saver = null")
	public Integer getPayedSellBillCountByDate(String dateFrom, String dateTo);

	public BillSell getBillSellByUpdaterId(String updaterId);

	public BillSell getBillSellBySaverId(String saverId);

}

package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillBuyItem;

public interface BillBuyItemsRepository extends CrudRepository<BillBuyItem, String> {

	@Query("SELECT SUM(bbi.quantity * bbi.buyPrice) FROM BillBuyItem bbi where date = CURRENT_DATE")
	public float getTotalBuysToday();

//	@Query("SELECT SUM(bbi.quantity * (bbi.buyPrice - bbi.sellPrice)) FROM BillBuyItem bbi where date = CURRENT_DATE")

}

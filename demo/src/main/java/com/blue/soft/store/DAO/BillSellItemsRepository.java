package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSellItem;

public interface BillSellItemsRepository extends CrudRepository<BillSellItem, String> {

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where date = CURRENT_DATE")
	public Float getTotalSallsToday();

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice -  bsi.buyPrice)) FROM BillSellItem bsi where date = CURRENT_DATE")
	public Float getTotalGains();

}

package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSellItem;

public interface BillSellItemsRepository extends CrudRepository<BillSellItem, String> {

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where date = CURRENT_DATE")
	public Float getTotalSalesToday();

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where bsi.billSell.late = 0 and bsi.date = CURRENT_DATE")
	public Float getTotalPayedSalesToday();

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where bsi.billSell.late = 1 and bsi.date = CURRENT_DATE")
	public Float getTotalLateSalesToday();

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice -  bsi.buyPrice)) FROM BillSellItem bsi where date = CURRENT_DATE")
	public Float getTotalGains();

}

package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSellItem;

public interface BillSellItemsRepository extends CrudRepository<BillSellItem, String> {

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where date between ?1 and ?2")
	public Float getTotalSalesByDate(String dateFrom, String dateTo);

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where bsi.billSell.late = 0 and date between ?1 and ?2")
	public Float getTotalPayedSalesByDate(String dateFrom, String dateTo);

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice)) FROM BillSellItem bsi where bsi.billSell.late = 1 and bsi.date between ?1 and ?2")
	public Float getTotalLateSalesByDate(String dateFrom, String dateTo);

	@Query("SELECT SUM(bsi.quantity * (bsi.sellPrice -  bsi.buyPrice)) FROM BillSellItem bsi where date between ?1 and ?2")
	public Float getTotalGainsByDate(String dateFrom, String dateTo);

}

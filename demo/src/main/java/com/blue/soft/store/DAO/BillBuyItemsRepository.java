package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillBuyItem;

public interface BillBuyItemsRepository extends CrudRepository<BillBuyItem, String> {

	@Query(value = "SELECT SUM(bbi.quantity * bbi.buyPrice) FROM BillBuyItem bbi where date between ?1 and ?2")
	public Float getTotalBuysByDate(String dateFrom, String dateTo);

}

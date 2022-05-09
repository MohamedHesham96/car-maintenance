package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillReturnItem;

public interface BillReturnItemsRepository extends CrudRepository<BillReturnItem, String> {

	@Query("SELECT SUM(bri.quantity * (bri.returnPrice)) FROM BillReturnItem bri where date between ?1 and ?2")
	public Float getTotalClientsReturnsByDate(String dateFrom, String dateTo);

}

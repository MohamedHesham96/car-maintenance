package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.CompanyBillReturnItem;

public interface CompanyBillReturnItemsRepository extends CrudRepository<CompanyBillReturnItem, String> {

	@Query("SELECT SUM(cbri.quantity * (cbri.returnPrice)) FROM CompanyBillReturnItem cbri where date = CURRENT_DATE")
	public Float getTotalCompaniesReturnsToday();

}

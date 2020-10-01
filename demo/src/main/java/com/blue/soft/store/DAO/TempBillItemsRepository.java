package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.TempBillItem;

public interface TempBillItemsRepository extends CrudRepository<TempBillItem, String> {

	public List<TempBillItem> findByBillIdAndBillType(String billId, String billType);

}

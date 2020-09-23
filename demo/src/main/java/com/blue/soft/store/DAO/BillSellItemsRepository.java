package com.blue.soft.store.DAO;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillSell;
import com.blue.soft.store.entity.BillSellItem;

public interface BillSellItemsRepository extends CrudRepository<BillSellItem, String> {

}

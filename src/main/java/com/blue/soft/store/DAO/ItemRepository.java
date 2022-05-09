package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Item;

public interface ItemRepository extends CrudRepository<Item, String> {

	@Query("SELECT SUM(i.quantity * i.buyPrice) FROM Item i")
	public Float getTotalItemsBuys();

	@Query("SELECT SUM(i.quantity * i.sellPrice) FROM Item i")
	public Float getTotalItemsSales();

	@Query("SELECT COUNT(i) FROM Item i")
	public Integer getItemsCount();

}

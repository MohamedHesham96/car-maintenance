package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.ItemMove;

public interface ItemMoveRepository extends CrudRepository<ItemMove, String> {

	public List<ItemMove> findByItemId(String itemId);

	public List<ItemMove> findByItemIdAndDateBetweenAndTypeContains(String itemId, String dateFrom, String dateTo,
			String type);

}

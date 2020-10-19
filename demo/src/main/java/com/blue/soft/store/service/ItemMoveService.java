package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.ItemMoveRepository;
import com.blue.soft.store.entity.ItemMove;

@Service
public class ItemMoveService {

	@Autowired
	ItemMoveRepository itemMoveRepository;

	public List<ItemMove> getAllItemMovesByDate(String itemId, String dateFrom, String dateTo) {

		return itemMoveRepository.findByItemIdAndDateBetween(itemId, dateFrom, dateTo);
	}

	public List<ItemMove> getItemMovesByItemId(String itemId) {

		return itemMoveRepository.findByItemId(itemId);
	}

	public void addItemMove(ItemMove theItemMove) {

		itemMoveRepository.save(theItemMove);

	}

}

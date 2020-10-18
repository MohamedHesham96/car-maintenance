package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.ItemMoveRepository;
import com.blue.soft.store.entity.ItemMove;

@Service
public class ItemMoveService {

	@Autowired
	ItemMoveRepository itemMoveRepository;

//	public List<ItemMove> getAllItemMoves() {
//
//		return (List<ItemMove>) itemMoveRepository.findAll();
//	}

	public ItemMove getItemById(String id) {

		return itemMoveRepository.findById(id).get();
	}

	public void addItemMove(ItemMove theItemMove) {

		itemMoveRepository.save(theItemMove);

	}

}

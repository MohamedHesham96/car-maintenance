package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.ItemRepository;
import com.blue.soft.store.entity.Item;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	public List<Item> getAllItems() {

		return (List<Item>) itemRepository.findAll();
	}

	public Item getItemById(String id) {

		return itemRepository.findById(id).get();
	}

	public void addNewItem(Item theItem) {

		itemRepository.save(theItem);

	}

	public Float getTotalItemsBuys() {

		Float totalItemsBuys = itemRepository.getTotalItemsBuys();

		return totalItemsBuys != null ? totalItemsBuys : 0;

	};

	public Float getTotalItemsSales() {

		Float totalItemsSales = itemRepository.getTotalItemsSales();

		return totalItemsSales != null ? totalItemsSales : 0;

	};

	public int getItemsCount() {

		Integer itemsCount = itemRepository.getItemsCount();

		return itemsCount != null ? itemsCount : 0;

	};
}

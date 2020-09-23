package com.blue.soft.store.DAO;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Item;

public interface ItemRepository extends CrudRepository<Item, String> {

}

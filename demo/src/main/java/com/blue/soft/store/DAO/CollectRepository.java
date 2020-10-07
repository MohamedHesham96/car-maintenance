package com.blue.soft.store.DAO;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Collect;
import com.blue.soft.store.entity.Spend;

public interface CollectRepository extends CrudRepository<Collect, String> {
	

}
package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Spend;

public interface SpendRepository extends CrudRepository<Spend, String> {

	public List<Spend> findAllByOrderByIdDesc();
}

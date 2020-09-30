package com.blue.soft.store.DAO;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, String> {

}

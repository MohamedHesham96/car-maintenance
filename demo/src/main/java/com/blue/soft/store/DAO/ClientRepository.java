package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Client;

public interface ClientRepository extends CrudRepository<Client, String> {

	List<Client> findByNameContaining(String theClientName);

}

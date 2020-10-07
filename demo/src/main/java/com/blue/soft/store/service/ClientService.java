package com.blue.soft.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.ClientRepository;
import com.blue.soft.store.entity.Client;
import com.blue.soft.store.entity.Collect;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	public List<Client> getAllClients() {

		return (List<Client>) clientRepository.findAll();
	}

	public void addNewClient(Client client) {

		clientRepository.save(client);

	}

	public Client getClientById(String id) {

		return clientRepository.findById(id).get();
	}

	public List<Client> searchForClient(String theClientName) {

		return (List<Client>) clientRepository.findByNameContaining(theClientName);

	}

	public float getDraweeTotal() {
		return clientRepository.getDraweeTotal();
	}
}

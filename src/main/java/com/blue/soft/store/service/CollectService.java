package com.blue.soft.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.CollectRepository;
import com.blue.soft.store.entity.Collect;

@Service
public class CollectService {

	@Autowired
	CollectRepository collectRepository;

	public void deleteCollectById(String id) {

		collectRepository.deleteById(id);
	}

	public Collect getCollectById(String collectId) {

		return collectRepository.findById(collectId).get();
	}

}

package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, String> {

	List<Company> findByNameContaining(String theCompanyName);

}

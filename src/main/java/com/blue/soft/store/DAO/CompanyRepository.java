package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, String> {

	List<Company> findByNameContaining(String theCompanyName);

	@Query("SELECT SUM(c.drawee) FROM Company c")
	public Float getDraweeTotal();

}

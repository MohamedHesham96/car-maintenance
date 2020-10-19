package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.CompanyBillReturn;

public interface CompanyBillReturnRepository extends CrudRepository<CompanyBillReturn, String> {

	public CompanyBillReturn findTopByOrderByIdDesc();

	public List<CompanyBillReturn> findByIdContainingOrderByIdDesc(String billId);

	public List<CompanyBillReturn> findAllByOrderByIdDesc();

	@Query("SELECT COUNT(cbr) FROM CompanyBillReturn cbr where date = CURRENT_DATE and saver = null")
	public Integer getCompanyBillReturnCountToday();

	public CompanyBillReturn getCompanyBillReturnByUpdaterId(String updaterId);

	public CompanyBillReturn getCompanyBillReturnBySaverId(String saverId);

}

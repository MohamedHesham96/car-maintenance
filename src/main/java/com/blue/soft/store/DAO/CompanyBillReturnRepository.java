package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.CompanyBillReturn;

public interface CompanyBillReturnRepository extends CrudRepository<CompanyBillReturn, String> {

	public CompanyBillReturn findTopByOrderByIdDesc();

	public List<CompanyBillReturn> findByIdContainingOrderByIdDesc(String billId);

	public List<CompanyBillReturn> findAllByOrderByIdDesc();

	@Query("SELECT COUNT(cbr) FROM CompanyBillReturn cbr where date between ?1 and ?2 and saver = null")
	public Integer getCompanyBillReturnCountByDate(String dateFrom, String dateTo);

	public CompanyBillReturn getCompanyBillReturnByUpdaterId(String updaterId);

	public CompanyBillReturn getCompanyBillReturnBySaverId(String saverId);

}

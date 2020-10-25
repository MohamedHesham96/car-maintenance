package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.BillReturn;

public interface BillReturnRepository extends CrudRepository<BillReturn, String> {

	public BillReturn findTopByOrderByIdDesc();

	public List<BillReturn> findByIdContainingOrderByIdDesc(String billId);

	public List<BillReturn> findAllByOrderByIdDesc();

	@Query("SELECT COUNT(br) FROM BillReturn br where date between ?1 and ?2 and saver = null")
	public Integer getReturnBillCountByDate(String dateFrom, String dateTo);

	public BillReturn getBillReturnByUpdaterId(String updaterId);

	public BillReturn getBillReturnBySaverId(String saverId);

}

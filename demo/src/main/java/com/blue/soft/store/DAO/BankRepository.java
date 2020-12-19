package com.blue.soft.store.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, String> {

	@Query(value = "FROM Bank b where date = CURRENT_DATE")
	public Bank getBankToDay();

	@Query("SELECT SUM(balance) FROM Bank b where date between ?1 and ?2")
	public Float getBankBalanceByDate(String dateFrom, String dateTo);

	@Query("SELECT SUM(balanceToday) FROM Bank b where date between ?1 and ?2")
	public Float getBankBalanceTodayByDate(String dateFrom, String dateTo);

	public Bank findTopByOrderByIdDesc();

}

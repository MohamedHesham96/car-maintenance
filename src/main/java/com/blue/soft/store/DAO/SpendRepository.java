package com.blue.soft.store.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.blue.soft.store.entity.Spend;

public interface SpendRepository extends CrudRepository<Spend, String> {

	public List<Spend> findByTypeOrderByIdDesc(String type);

	@Query("SELECT SUM(s.amount) FROM Spend s where s.type = 'مصروف'")
	public Float getSpendTotal();

	@Query("SELECT SUM(s.amount) FROM Spend s where s.type = 'مصروف' and date between ?1 and ?2")
	public Float getSpendTotalByDate(String dateFrom, String dateTo);

	@Query("SELECT SUM(s.amount) FROM Spend s where s.type = 'رصيد'")
	public Float getBalancesTotal();

	@Query("SELECT SUM(s.amount) FROM Spend s where s.type = 'رصيد' and date between ?1 and ?2")
	public Float getBalancesTotalByDate(String dateFrom, String dateTo);
}
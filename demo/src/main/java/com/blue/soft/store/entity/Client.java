package com.blue.soft.store.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "drawee")
	private float drawee;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillSell> billSellList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillReturn> billReturnList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	@OrderBy(value = "time_stamp DESC")
	private List<Collect> collectList;

	public Client() {
	}

	public float getDrawee() {
		return this.drawee;
	}

	public void setDrawee(float f) {
		this.drawee = f;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BillSell> getBillSellList() {
		return billSellList;
	}

	public void BillSellList(List<BillSell> billSell) {
		this.billSellList = billSell;
	}

	public List<Collect> getCollectList() {
		return collectList;
	}

	public void setCollectList(List<Collect> collectList) {
		this.collectList = collectList;
	}

	public void addCollect(Collect collect) {

		collectList.add(collect);
	}

	public List<BillReturn> getBillReturnList() {
		return billReturnList;
	}

	public void setBillReturnList(List<BillReturn> billReturnList) {
		this.billReturnList = billReturnList;
	}

	public void setBillSellList(List<BillSell> billSellList) {
		this.billSellList = billSellList;
	}

}
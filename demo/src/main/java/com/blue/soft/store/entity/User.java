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

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "type")
	private String type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillSell> billSellList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillBuy> billBuyList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillReturn> billReturnList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}

	public List<BillSell> getBillSellList() {
		return billSellList;
	}

	public void setBillSellList(List<BillSell> billSellList) {
		this.billSellList = billSellList;
	}

	public List<BillBuy> getBillBuyList() {
		return billBuyList;
	}

	public void setBillBuyList(List<BillBuy> billBuyList) {
		this.billBuyList = billBuyList;
	}

	public List<BillReturn> getBillReturnList() {
		return billReturnList;
	}

	public void setBillReturnList(List<BillReturn> billReturnList) {
		this.billReturnList = billReturnList;
	}

}
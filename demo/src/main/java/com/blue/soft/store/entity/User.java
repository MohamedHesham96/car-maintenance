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

	@Column(name = "main")
	private boolean hasMain;

	@Column(name = "sales")
	private boolean hasSales;

	@Column(name = "buys")
	private boolean hasBuys;

	@Column(name = "store")
	private boolean hasStore;

	@Column(name = "item_move")
	private boolean hasItemMove;

	@Column(name = "clients")
	private boolean hasClients;

	@Column(name = "companies")
	private boolean hasCompanies;

	@Column(name = "reports")
	private boolean hasReports;

	@Column(name = "bank ")
	private boolean hasBank;

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

	public boolean isHasMain() {
		return hasMain;
	}

	public void setHasMain(boolean hasMain) {
		this.hasMain = hasMain;
	}

	public boolean isHasSales() {
		return hasSales;
	}

	public void setHasSales(boolean hasSales) {
		this.hasSales = hasSales;
	}

	public boolean isHasBuys() {
		return hasBuys;
	}

	public void setHasBuys(boolean hasBuys) {
		this.hasBuys = hasBuys;
	}

	public boolean isHasStore() {
		return hasStore;
	}

	public void setHasStore(boolean hasStore) {
		this.hasStore = hasStore;
	}

	public boolean isHasItemMove() {
		return hasItemMove;
	}

	public void setHasItemMove(boolean hasItemMove) {
		this.hasItemMove = hasItemMove;
	}

	public boolean isHasClients() {
		return hasClients;
	}

	public void setHasClients(boolean hasClients) {
		this.hasClients = hasClients;
	}

	public boolean isHasCompanies() {
		return hasCompanies;
	}

	public void setHasCompanies(boolean hasCompanies) {
		this.hasCompanies = hasCompanies;
	}

	public boolean isHasReports() {
		return hasReports;
	}

	public void setHasReports(boolean hasReports) {
		this.hasReports = hasReports;
	}

	public boolean isHasBank() {
		return hasBank;
	}

	public void setHasBank(boolean hasBank) {
		this.hasBank = hasBank;
	}

}
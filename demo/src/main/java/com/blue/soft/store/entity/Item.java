package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item  {
	private static final long serialVersionUID = 1L;

	@Column(name="buy_price")
	private float buyPrice;

	@Column(name="company_id")
	private int companyId;

	private String name;

	private int quantity;

	@Column(name="sell_price")
	private float sellPrice;

	//bi-directional many-to-many association to BillSell
	@ManyToMany
	@JoinTable(
		name="bill_sell_items"
		, joinColumns={
			@JoinColumn(name="item_id", referencedColumnName="id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="bill_id", referencedColumnName="id")
			}
		)
	private List<BillSell> billSells;

	//bi-directional many-to-many association to BillReturn
	@ManyToMany
	@JoinTable(
		name="bill_return_items"
		, joinColumns={
			@JoinColumn(name="item_id", referencedColumnName="id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="bill_id", referencedColumnName="id")
			}
		)
	private List<BillReturn> billReturns;

	public Item() {
	}

	public float getBuyPrice() {
		return this.buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public List<BillSell> getBillSells() {
		return this.billSells;
	}

	public void setBillSells(List<BillSell> billSells) {
		this.billSells = billSells;
	}

	public List<BillReturn> getBillReturns() {
		return this.billReturns;
	}

	public void setBillReturns(List<BillReturn> billReturns) {
		this.billReturns = billReturns;
	}

}
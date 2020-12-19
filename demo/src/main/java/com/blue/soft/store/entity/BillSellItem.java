package com.blue.soft.store.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_sell_items")
public class BillSellItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "bill_id")
	private BillSell billSell;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "sell_price")
	private float sellPrice;

	@Column(name = "buy_price")
	private float buyPrice;

	@Column(name = "date")
	private String date;

	public BillSellItem() {
		super();
	}

	public BillSellItem(BillSell billSell, Item item, int quantity, float sellPrice) {
		super();
		this.billSell = billSell;
		this.item = item;
		this.quantity = quantity;
		this.sellPrice = sellPrice;
		this.date = LocalDate.now().toString();
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BillSell getBillSell() {
		return billSell;
	}

	public void setBillSell(BillSell billSell) {
		this.billSell = billSell;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
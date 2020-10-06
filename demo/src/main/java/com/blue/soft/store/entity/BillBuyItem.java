package com.blue.soft.store.entity;

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
@Table(name = "bill_buy_items")
public class BillBuyItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "Bill_id")
	private BillBuy billBuy;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "buy_price")
	private float buyPrice;

	public BillBuyItem() {
		super();
	}

	public BillBuyItem(BillBuy billBuy, Item item, int quantity, float buyPrice) {
		super();
		this.billBuy = billBuy;
		this.item = item;
		this.quantity = quantity;
		this.buyPrice = buyPrice;

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

	public BillBuy getBillBuy() {
		return billBuy;
	}

	public void setBillBuy(BillBuy billBuy) {
		this.billBuy = billBuy;
	}

}
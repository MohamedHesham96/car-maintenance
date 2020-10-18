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
@Table(name = "item_moves")
public class ItemMove {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "item_id")
	private Item item;

	@Column(name = "type")
	private String type;

	@Column(name = "bill_id")
	private int billId;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "balance")
	private int balance;

	@Column(name = "buy_price")
	private float buyPrice;

	@Column(name = "sell_price")
	private float sellPrice;

	public ItemMove() {
		super();
	}

	public ItemMove(Item item, int billId, int quantity, int balance, float buyPrice, float sellPrice) {
		super();
		this.item = item;
		this.billId = billId;
		this.quantity = quantity;
		this.balance = balance;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

}
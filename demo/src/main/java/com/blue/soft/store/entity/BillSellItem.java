package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bill_sell_items database table.
 * 
 */
@Entity
@Table(name="bill_sell_items")
@NamedQuery(name="BillSellItem.findAll", query="SELECT b FROM BillSellItem b")
public class BillSellItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="bill_id")
	private int billId;

	@Column(name="buy_price")
	private int buyPrice;

	private int id;

	@Column(name="item_id")
	private int itemId;

	private int quantity;

	@Column(name="sell_price")
	private int sellPrice;

	public BillSellItem() {
	}

	public int getBillId() {
		return this.billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getBuyPrice() {
		return this.buyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

}
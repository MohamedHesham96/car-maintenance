package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bill_return_items database table.
 * 
 */
@Entity
@Table(name="bill_return_items")
@NamedQuery(name="BillReturnItem.findAll", query="SELECT b FROM BillReturnItem b")
public class BillReturnItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="bill_id")
	private int billId;

	private int id;

	@Column(name="item_id")
	private int itemId;

	@Column(name="sell_price")
	private int sellPrice;

	public BillReturnItem() {
	}

	public int getBillId() {
		return this.billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
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

	public int getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}

}
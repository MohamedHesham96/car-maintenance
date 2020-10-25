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
@Table(name = "bill_return_items")
public class BillReturnItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "return_price")
	private float returnPrice;

	@Column(name = "date")
	private String date;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "item_id")
	private Item item;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "Bill_id")
	private BillReturn billReturn;

	public BillReturnItem() {
		super();
	}

	public BillReturnItem(BillReturn billReturn, Item item, int quantity, float returnPrice) {
		super();
		this.billReturn = billReturn;
		this.item = item;
		this.quantity = quantity;
		this.returnPrice = returnPrice;
		this.date = LocalDate.now().toString();

	}

	public float getReturnPrice() {
		return returnPrice;
	}

	public void setReturnPrice(float returnPrice) {
		this.returnPrice = returnPrice;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public BillReturn getBillReturn() {
		return billReturn;
	}

	public void setBillReturn(BillReturn billReturn) {
		this.billReturn = billReturn;
	}

}
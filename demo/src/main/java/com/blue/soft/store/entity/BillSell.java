package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill_sell")
public class BillSell {

	@Column(name = "client_id")
	private int clientId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int payed;

	private String type;

	// bi-directional many-to-many association to Item
	@ManyToMany(mappedBy = "billSells")
	private List<Item> items;

	public BillSell() {
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPayed() {
		return this.payed;
	}

	public void setPayed(int payed) {
		this.payed = payed;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
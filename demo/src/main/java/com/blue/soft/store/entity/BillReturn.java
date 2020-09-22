package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bill_return database table.
 * 
 */
@Entity
@Table(name="bill_return")
@NamedQuery(name="BillReturn.findAll", query="SELECT b FROM BillReturn b")
public class BillReturn  {
	private static final long serialVersionUID = 1L;

	@Column(name="client_id")
	private int clientId;

	private int date;

	//bi-directional many-to-many association to Item
	@ManyToMany(mappedBy="billReturns")
	private List<Item> items;

	public BillReturn() {
	}

	public int getClientId() {
		return this.clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getDate() {
		return this.date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
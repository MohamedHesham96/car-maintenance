package com.blue.soft.store.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

@Entity
@Table(name = "bill_buy")
public class BillBuy {

	@Column(name = "company_id")
	private int companyId;

	@Column(name = "date")
	private Date date;

	// bi-directional many-to-many association to Item
	@ManyToMany
	@JoinTable(name = "bill_buy_items", joinColumns = {
			@JoinColumn(name = "bill_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "item_id", referencedColumnName = "id") })
	private List<Item> items;

	public BillBuy() {
	}

	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
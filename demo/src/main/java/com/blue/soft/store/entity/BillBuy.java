package com.blue.soft.store.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bill_buy")
public class BillBuy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "date")
	private String date;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "updater_id")
	private User updater;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "saver_id")
	private User saver;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "billBuy", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillBuyItem> billBuyItems;

	public BillBuy() {
	}

	public void addBillBuyItem(BillBuyItem billBuyItem) {

		billBuyItems.add(billBuyItem);
	}

	public List<BillBuyItem> getBillBuyItems() {
		return billBuyItems;
	}

	public void setBillBuyItem(List<BillBuyItem> billBuyItems) {
		this.billBuyItems = billBuyItems;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	public User getSaver() {
		return saver;
	}

	public void setSaver(User saver) {
		this.saver = saver;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setBillBuyItems(List<BillBuyItem> billBuyItems) {
		this.billBuyItems = billBuyItems;
	}

	public List<String> getBillBuyItemsIDS() {

		List<String> idsList = new ArrayList<String>();

		for (BillBuyItem billBuyItem : billBuyItems) {

			idsList.add(billBuyItem.getId());
		}

		return idsList;
	}

	public void removeItem(BillBuyItem billBuyItem) {

		billBuyItems.remove(billBuyItem);
	}

	public float getTotal() {

		float total = 0;

		for (BillBuyItem billBuyItem : billBuyItems) {

			total += billBuyItem.getBuyPrice() * billBuyItem.getQuantity();
		}

		return total;
	}
}
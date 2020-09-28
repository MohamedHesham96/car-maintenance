package com.blue.soft.store.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill_buy")
public class BillBuy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "date")
	private String date;

	@Column(name = "late")
	private boolean late;

	@Column(name = "saved")
	private boolean saved;

	@Column(name = "update_now")
	private boolean updateNow;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "billBuy", cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public void setBillBuyItems(List<BillBuyItem> billBuyItems) {
		this.billBuyItems = billBuyItems;
	}

	public boolean isUpdateNow() {
		return updateNow;
	}

	public void setUpdateNow(boolean updateNow) {
		this.updateNow = updateNow;
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
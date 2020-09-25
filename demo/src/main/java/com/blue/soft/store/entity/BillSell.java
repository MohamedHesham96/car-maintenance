package com.blue.soft.store.entity;

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
@Table(name = "bill_sell")
public class BillSell {

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

	@OneToMany(mappedBy = "billSell", cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<BillSellItem> billSellItems;

	public BillSell() {
	}

	public void addBillSellItem(BillSellItem billSellItem) {

		billSellItems.add(billSellItem);
	}

	public List<BillSellItem> getBillSellItems() {
		return billSellItems;
	}

	public void setBillSellItem(List<BillSellItem> billSellItems) {
		this.billSellItems = billSellItems;
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

	public void setBillSellItems(List<BillSellItem> billSellItems) {
		this.billSellItems = billSellItems;
	}

	public boolean isUpdateNow() {
		return updateNow;
	}

	public void setUpdateNow(boolean updateNow) {
		this.updateNow = updateNow;
	}

	public void removeAll() {

		billSellItems = null;
	}
}
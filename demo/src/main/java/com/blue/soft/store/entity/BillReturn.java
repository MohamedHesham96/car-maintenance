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
@Table(name = "bill_return")
public class BillReturn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "date")
	private String date;

	@Column(name = "saved")
	private boolean saved;

	@Column(name = "update_now")
	private boolean updateNow;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "client_id")
	private Client client;

	@OneToMany(mappedBy = "billReturn", cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	private List<BillReturnItem> billReturnItems;

	public void addBillReturnItem(BillReturnItem billReturnItem) {

		billReturnItems.add(billReturnItem);
	}

	public List<BillReturnItem> getBillReturnItems() {
		return billReturnItems;
	}

	public void setBillReturnItem(List<BillReturnItem> billReturnItems) {
		this.billReturnItems = billReturnItems;
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

	public boolean isSaved() {
		return saved;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	public void setBillReturnItems(List<BillReturnItem> billReturnItems) {
		this.billReturnItems = billReturnItems;
	}

	public boolean isUpdateNow() {
		return updateNow;
	}

	public void setUpdateNow(boolean updateNow) {
		this.updateNow = updateNow;
	}

	public List<String> getBillReturnItemsIDS() {

		List<String> idsList = new ArrayList<String>();

		for (BillReturnItem billReturnItem : billReturnItems) {

			idsList.add(billReturnItem.getId());
		}

		return idsList;
	}

	public void removeItem(BillReturnItem billReturnItem) {

		billReturnItems.remove(billReturnItem);
	}

	public float getTotal() {

		float total = 0;

		for (BillReturnItem billReturnItem : billReturnItems) {

			total += billReturnItem.getReturnPrice() * billReturnItem.getQuantity();
		}

		return total;
	}
}
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
@Table(name = "company_bill_return")
public class CompanyBillReturn {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "date")
	private String date;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyBillReturn", cascade = { CascadeType.DETACH,
			CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<CompanyBillReturnItem> companyBillReturnItems;

	public void addCompanyBillReturnItem(CompanyBillReturnItem companyBillReturnItem) {

		companyBillReturnItems.add(companyBillReturnItem);
	}

	public List<CompanyBillReturnItem> getCompanyBillReturnItems() {
		return companyBillReturnItems;
	}

	public void setCompanyBillReturnItem(List<CompanyBillReturnItem> companyBillReturnItems) {
		this.companyBillReturnItems = companyBillReturnItems;
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

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setCompanyBillReturnItems(List<CompanyBillReturnItem> companyBillReturnItems) {
		this.companyBillReturnItems = companyBillReturnItems;
	}

	public List<String> getCompanyBillReturnItemsIDS() {

		List<String> idsList = new ArrayList<String>();

		for (CompanyBillReturnItem companyBillReturnItem : companyBillReturnItems) {

			idsList.add(companyBillReturnItem.getId());
		}

		return idsList;
	}

	public void removeItem(CompanyBillReturnItem companyBillReturnItem) {

		companyBillReturnItems.remove(companyBillReturnItem);
	}

	public float getTotal() {

		float total = 0;

		for (CompanyBillReturnItem companyBillReturnItem : companyBillReturnItems) {

			total += companyBillReturnItem.getReturnPrice() * companyBillReturnItem.getQuantity();
		}

		return total;
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

}
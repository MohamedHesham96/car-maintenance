package com.blue.soft.store.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "drawee")
	private float drawee;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	@OrderBy(value = "time_stamp DESC")
	private List<Pay> payList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = { CascadeType.DETACH, CascadeType.PERSIST,
			CascadeType.MERGE, CascadeType.REFRESH })
	private List<BillBuy> billBuyList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pay> getPayList() {
		return payList;
	}

	public void setPayList(List<Pay> payList) {
		this.payList = payList;
	}

	public float getDrawee() {
		return drawee;
	}

	public void setDrawee(float drawee) {
		this.drawee = drawee;
	}

	public void addPay(Pay pay) {

		payList.add(pay);

	}

	public List<BillBuy> getBillBuyList() {
		return billBuyList;
	}

	public void setBillBuyList(List<BillBuy> billBuyList) {
		this.billBuyList = billBuyList;
	}

}
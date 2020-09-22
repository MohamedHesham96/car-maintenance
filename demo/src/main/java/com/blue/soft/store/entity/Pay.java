package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the pay database table.
 * 
 */
@Entity
@NamedQuery(name="Pay.findAll", query="SELECT p FROM Pay p")
public class Pay implements Serializable {
	private static final long serialVersionUID = 1L;

	private int amount;

	@Column(name="company_id")
	private int companyId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int id;

	public Pay() {
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
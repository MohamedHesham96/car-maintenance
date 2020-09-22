package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the collect database table.
 * 
 */
@Entity
@NamedQuery(name="Collect.findAll", query="SELECT c FROM Collect c")
public class Collect implements Serializable {
	private static final long serialVersionUID = 1L;

	private int amount;

	@Column(name="client_id")
	private int clientId;

	@Temporal(TemporalType.DATE)
	private Date date;

	private int id;

	public Collect() {
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
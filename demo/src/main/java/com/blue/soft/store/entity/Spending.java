package com.blue.soft.store.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the spending database table.
 * 
 */
@Entity
public class Spending {

	@Temporal(TemporalType.DATE)
	private Date date;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int money;

	private String note;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	public Spending() {
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

	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
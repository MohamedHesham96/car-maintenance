package com.blue.soft.store.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "balance")
	private float balance;

	@Column(name = "balance_today")
	private float balanceToday;

	@Column(name = "date")
	private String date;

	public Bank() {
	}

	public Bank(float balance, float balanceToday, String date) {
		this.balance = balance;
		this.balanceToday = balanceToday;
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getBalanceToday() {
		return balanceToday;
	}

	public void setBalanceToday(float balanceToday) {
		this.balanceToday = balanceToday;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
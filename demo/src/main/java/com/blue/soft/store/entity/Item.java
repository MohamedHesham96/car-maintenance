package com.blue.soft.store.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Item {

	public List<ItemMove> getItemMoves() {
		return itemMoves;
	}

	public void setItemMoves(List<ItemMove> itemMoves) {
		this.itemMoves = itemMoves;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "buy_price")
	private float buyPrice;

	@Column(name = "sell_price")
	private float sellPrice;

	@OneToMany(mappedBy = "item", cascade = { CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REFRESH })
	@OrderBy(value = "time_stamp asc")
	private List<ItemMove> itemMoves;

	public Item() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(float buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSellPrice() {
		return this.sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

}
package com.shopping;

public class Item implements Comparable<Item> {

	private String name;
	private double price;
	private int quantity;
	private int reserved;

	public Item(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public Item(String name, double price) {
		this.name = name;
		this.price = price;
		this.quantity = 0;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity - reserved;
	}

	public int getReserved() {
		return reserved;
	}

	public void adjustStock(int value) {
		int newStock = quantity + value;
		if (newStock >= 0) {
			quantity = newStock;
		}
	}

	public int reserveQuantity(int quantity) {
		if (getQuantity() >= quantity) {
			reserved += quantity;
			return quantity;
		}
		return 0;
	}

	public int unreserveQuantity(int quantity) {
		if (reserved >= quantity) {
			reserved -= quantity;
			return quantity;
		}
		return 0;
	}

	public int finalizeQuantity(int quantity) {
		if (reserved >= quantity) {
			this.quantity -= quantity;
			reserved -= quantity;
			return quantity;
		}
		return 0;
	}

	@Override
	public int compareTo(Item o) {
		if (this == o) {
			return 0;
		}

		if (o != null) {
			return this.name.compareTo(o.getName());
		}

		throw new NullPointerException();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}

		String name = ((Item) obj).getName();

		return this.getName().equals(name);
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() + 8;
	}

	@Override
	public String toString() {
		return "Name of Item: " + this.name + " |\tQuantity: " + this.quantity + " |\tPrice: " + this.price
				+ " |\tReserved: " + this.reserved;
	}

}

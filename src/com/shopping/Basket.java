package com.shopping;

import java.util.Map;
import java.util.TreeMap;

public class Basket {

	private String name;
	private Map<Item, Integer> basketList;

	public Basket(String name) {
		this.name = name;
		this.basketList = new TreeMap<>();
	}

	public int addToBasket(Item item, int quantity) {
		if (item != null && quantity > 0) {
			int inBasket = basketList.getOrDefault(item, 0);
			basketList.put(item, inBasket + quantity);
			return inBasket;
		}
		return 0;
	}

	public int removeFromBasket(Item item, int quantity) {

		int amount = basketList.get(item);
		if (item != null && amount > quantity) {
			basketList.put(item, amount - quantity);
			return quantity;
		} else if (item != null && amount == quantity) {
			basketList.remove(item);
			return quantity;
		}
		return 0;
	}

	public void clearBasket() {
		basketList.clear();
	}

	public Map<Item, Integer> getBasketList() {
		return basketList;
	}

	public void setBasketList(Map<Item, Integer> basketList) {
		this.basketList = basketList;
	}

	@Override
	public String toString() {
		String s = "Basket Details :\n";
		s = s + "---------------------------------------------------------------";
		s = s + "\nShopping Basket of " + this.name + " contains " + basketList.size()
				+ ((basketList.size() == 1) ? " item." : " items.\n");
		double totalCost = 0.0;
		for (Map.Entry<Item, Integer> item : basketList.entrySet()) {
			s = s + "\nItem Name: " + item.getKey().getName() + " with Quantity " + item.getValue() + " in basket.";
			totalCost += item.getKey().getPrice() * item.getValue();
		}
		s = s + " \nTotal Cost " + totalCost;
		return s + "\n---------------------------------------------------------------\n\n";
	}

}

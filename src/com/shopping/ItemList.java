package com.shopping;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ItemList {
	private final Map<String, Item> list;

	public ItemList() {
		this.list = new LinkedHashMap<String, Item>();
	}

	public int addStock(Item item) {

		if (item != null) {
			Item inStock = list.getOrDefault(item.getName(), item);

			if (inStock != item) {
				inStock.adjustStock(item.getQuantity());
			}

			list.put(item.getName(), item);
			return item.getQuantity();
		}
		return 0;
	}

	public int sellStock(String item, int quantity) {

		Item inStock = list.getOrDefault(item, null);
		if (inStock != null && quantity > 0) {
			inStock.finalizeQuantity(quantity);
		}
//      old code
//		if (inStock != null && (inStock.getQuantity() >= quantity) && (quantity > 0)) {
//			inStock.adjustStock(-quantity);
//			return quantity;
//		}
		return 0;
	}

	public int reserveQuantity(String name, int quantity) {
		Item item = list.get(name);

		if (item != null && quantity > 0) {
			return item.reserveQuantity(quantity);
		}
		return 0;
	}

	public int unreserveQuantity(String name, int quantity) {
		Item item = list.get(name);

		if (item != null && quantity > 0) {
			return item.unreserveQuantity(quantity);
		}
		return 0;
	}

	public Map<String, Double> priceList() {
		Map<String, Double> prices = new HashMap<>();
		for (Map.Entry<String, Item> item : list.entrySet()) {
			prices.put(item.getKey(), item.getValue().getPrice());
		}
		return Collections.unmodifiableMap(prices);
	}

	public Item getItem(String name) {
		return list.get(name);
	}

	public Map<String, Item> getList() {
		return Collections.unmodifiableMap(list);
	}

	@Override
	public String toString() {
		String s = "Details of Item List :\n";
		s = s + "---------------------------------------------------------------";
		s = s + "\nStock Info:\n";
		double totalCost = 0.0;

		for (Map.Entry<String, Item> item : list.entrySet()) {
			Item instance = item.getValue();
			double itemCost = instance.getPrice() * instance.getQuantity();
			s = s + "\tItem Name: " + instance.getName() + " || Quantity: " + instance.getQuantity()
					+ " || Price of Each: " + instance.getPrice() + " || Reserved: " + instance.getReserved();
			s = s + "\n";
			totalCost += itemCost;
		}

		s = s + "\nTotal Cost: " + String.format("%.2f", totalCost);
		return s + "\n---------------------------------------------------------------\n\n";

	}

}

package com.shopping;

public class Main {
	private static ItemList itemList = new ItemList();

	public static void main(String[] args) {
		// Add Item 1
		Item temp = new Item("Kishan", 10, 5);
		itemList.addStock(temp);

		// Add Item Cycle to List
		temp = new Item("Cycle", 5.99, 50);
		itemList.addStock(temp);

		// Add Item Chair to List
		temp = new Item("Chair", 15, 4);
		itemList.addStock(temp);

		// Add Item Table to List
		temp = new Item("Table", 25, 1);
		itemList.addStock(temp);

		// Add Item Car to List
		temp = new Item("Car", 3500, 4);
		itemList.addStock(temp);

		// Print all Item
		System.out.println(itemList);

		// Create a Basket
		Basket kishanBasket = new Basket("kishansBasket");

		// Sell items
		sellItem(kishanBasket, "Chair", 1);
		sellItem(kishanBasket, "Chair", 1);
		sellItem(kishanBasket, "Car", 1);

		// Print Basket
		System.out.println(kishanBasket);
		// Print all Item
		System.out.println(itemList);

		removeItem(kishanBasket, "Chair", 1);

		// Print Basket
		System.out.println(kishanBasket);
		// Print all Item
		System.out.println(itemList);
	}

	public static int sellItem(Basket basket, String name, int quantity) {
		Item item = itemList.getItem(name);
		if (item == null) {
			System.out.println("We Don't sell " + item);
			return 0;
		}

		if (itemList.reserveQuantity(name, quantity) != 0) {
			basket.addToBasket(item, quantity);
			return quantity;
		}
		return 0;
	}

	public static int removeItem(Basket basket, String name, int quantity) {
		Item item = itemList.getItem(name);
		if (item == null) {
			System.out.println("We Don't sell " + item);
		}

		if (basket.removeFromBasket(item, quantity) == quantity) {
			itemList.unreserveQuantity(name, quantity);
		}
		return 0;
	}

}

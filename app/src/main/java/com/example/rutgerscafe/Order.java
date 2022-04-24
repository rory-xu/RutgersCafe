package com.example.rutgerscafe;

import java.util.*;

/**
 * This class creates an order that contains MenuItems
 * @author Rory Xu, Hassan Alfareed
 */
public class Order implements com.example.rutgerscafe.Customizable {
	private final int orderNumber;
	private List<MenuItem> items= new ArrayList<>();

	/**
	 * Constructs an order with an order number
	 * @param orderNumber The order number of the order
	 */
	public Order(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * Retrieves the list of MenuItems in the Order
	 * @return The list of MenuItems
	 */
	public List<MenuItem> getItems() {
		return items;
	}

	/**
	 * Retrieves the order number of the order
	 * @return The order number of the order
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Retrieves the subtotal of the items in the order
	 * @return The subtotal of all the items in the order
	 */
	public double getSubtotal() {
		double subtotal = 0;
		for (MenuItem item : items) {
			subtotal += item.itemPrice();
		}
		return subtotal;
	}

	/**
	 * Adds a MenuItem to the list of MenuItems
	 * @param obj The MenuItem to be added
	 * @return True if successful, false if not
	 */
	@Override
	public boolean add(Object obj) {
		if (obj instanceof MenuItem) {
			items.add((MenuItem) obj);
			return true;
		}
		return false;
	}

	/**
	 * Removes a MenuItem from the list of MenuItems
	 * @param obj The MenuItem to be removed
	 * @return True if successful, false if not
	 */
	@Override
	public boolean remove(Object obj) {
		if (obj instanceof MenuItem) {
			items.remove((MenuItem) obj);
			return true;
		}
		return false;
	}

	/**
	 * Checks if the order is equal to the parameter by comparing order numbers
	 * @param obj The order to be compared with
	 * @return True if the order is the same, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			return this.orderNumber == ((Order) obj).orderNumber;
		}
		return false;
	}

	/**
	 * Displays the order number of the order
	 * @return The order number
	 */
	@Override
	public String toString() {
		return "Order Number: " + orderNumber;
	}
}

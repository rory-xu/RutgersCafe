package com.example.rutgerscafe;


import java.util.*;

/**
 * This class creates a StoreOrders object that holds orders that came through the store
 * @author Rory Xu, Hassan Alfareed
 */
public class StoreOrders implements Customizable {

	private List<Order> orders= new ArrayList<>();

	/**
	 * Retrieves the list of orders
	 * @return The store orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Adds an Order to the list of Orders
	 * @param obj The Order to be added
	 * @return True if successful, false if not
	 */
	@Override
	public boolean add(Object obj) {
		if (obj instanceof Order) {
			orders.add((Order) obj);
			return true;
		}
		return false;
	}

	/**
	 * Removes an Order to the list of Orders
	 * @param obj The Order to be removed
	 * @return True if successful, false if not
	 */
	@Override
	public boolean remove(Object obj) {
		if (obj instanceof Order) {
			orders.remove((Order) obj);
			return true;
		}
		return false;
	}
}

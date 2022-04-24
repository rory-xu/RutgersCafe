package com.example.rutgerscafe;

import java.util.ArrayList;
import java.util.List;

/**
 * A coffee object that can have many addons
 * @author Rory Xu, Hassan Alfareed
 */
public class Coffee extends MenuItem implements com.example.rutgerscafe.Customizable {

	private List<String> addIns = new ArrayList<>();
	private double basePrice;
	private int quantity = 1;

	/**
	 * Constructs a coffee
	 * @param basePrice The base price of the coffee based on size
	 */
	public Coffee(double basePrice) {
		this.basePrice = basePrice;
	}

	/**
	 * Changes the base price of the coffee based on size
	 * @param newPrice The new base price
	 */
	public void changeBasePrice(double newPrice) {
		basePrice = newPrice;
	}

	/**
	 * Changes the number of coffees requested
	 * @param newQuantity The new number of coffees requested
	 */
	public void changeQuantity(int newQuantity) {
		quantity = newQuantity;
	}

	/**
	 * Adds add-ins to a list
	 * @param obj The add-in to be added
	 * @return True if successful, false if not
	 */
	@Override
	public boolean add(Object obj) {
		if (obj instanceof String) {
			addIns.add((String) obj);
			return true;
		}
		return false;
	}

	/**
	 * Removes add-ins from a list
	 * @param obj The add-in to be removed
	 * @return True if successful, false if not
	 */
	@Override
	public boolean remove(Object obj) {
		if (obj instanceof String && addIns.contains(obj)) {
			addIns.remove(obj);
			return true;
		}
		return false;
	}

	/**
	 * Calculates the price of the coffee based on size and add-ins
	 * @return The calculated price
	 */
	@Override
	public double itemPrice() {
		int addInsPrice = addIns.size();
		return (basePrice + 0.30 * addInsPrice) * quantity;
	}

	/**
	 * Displays the coffee order
	 * @return The order in text form
	 */
	@Override
	public String toString() {
		String display = "";
		switch (Double.toString(basePrice)) {
			case ("1.69"):
				display = "Coffee(" + quantity + ") Short " + (addIns.size() > 0 ? addIns.toString() : "[No add-ins]");
				break;
			case ("2.09"):
				display = "Coffee(" + quantity + ") Tall " + (addIns.size() > 0 ? addIns.toString() : "[No add-ins]");
				break;
			case ("2.49"):
				display = "Coffee(" + quantity + ") Grande " + (addIns.size() > 0 ? addIns.toString() : "[No add-ins]");
				break;
			case ("2.89"):
				display = "Coffee(" + quantity + ") Venti " + (addIns.size() > 0 ? addIns.toString() : "[No add-ins]");
				break;
			default:
				display = null;
				break;
		};
		return display;
	}
}

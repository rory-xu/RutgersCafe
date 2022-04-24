package com.example.rutgerscafe;

/**
 * This abstract class declares a general donut that can be further specified
 * @author Rory Xu, Hassan Alfareed
 */
public class Donut extends MenuItem{
	protected String flavor;
	protected int quantity;

	/**
	 * Constructs a donut
	 * @param flavor The flavor of the donut
	 * @param quantity The number of donuts requested
	 */
	public Donut(String flavor, int quantity) {
		this.flavor = flavor;
		this.quantity = quantity;
	}

	/**
	 * Displays the Donut order
	 * @return The order in text form
	 */
	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}

	/**
	 * Not used
	 * @return Not used
	 */
	@Override
	double itemPrice() {
		return 0;
	}
}

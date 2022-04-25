package com.example.rutgerscafe;

/**
 * A Yeast Donut object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
public class YeastDonut extends Donut {

	/**
	 * Constructs a yeast donut
	 * @param flavor The flavor of the donut
	 * @param quantity The number of this type of donut
	 */
	public YeastDonut(String flavor, int quantity) {
		super(flavor, quantity);

	}

	/**
	 * Calculates and returns the price of the yeast donut(s)
	 * @return The price of the yeast donut(s)
	 */
	@Override
	public double itemPrice() {
		return 1.59 * quantity;
	}

	/**
	 * Displays the yeast donut order
	 * @return The order in text form
	 */
	@Override
	public String toString() {
		return "Yeast Donut: " + flavor + "(" + quantity + ")";
	}
}

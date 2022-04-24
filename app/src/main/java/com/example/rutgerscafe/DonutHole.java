package com.example.rutgerscafe;

/**
 * A Donut Hole object with specific flavors
 * @author Rory Xu, Hassan Alfareed
 */
public class DonutHole extends Donut {

	/**
	 * Constructs a donut hole
	 * @param flavor The flavor of the donut hole
	 * @param quantity The number of donut holes requested
	 */
	public DonutHole(String flavor, int quantity) {
		super(flavor, quantity);
	}

	/**
	 * Calculates the price of the donut hole(s)
	 * @return The price of the donut hole(s)
	 */
	@Override
	public double itemPrice() {
		return 0.39 * quantity;
	}

	/**
	 * Displays the donut hole order
	 * @return The order in text form
	 */
	@Override
	public String toString() {
		return flavor + "(" + quantity + ")";
	}
}

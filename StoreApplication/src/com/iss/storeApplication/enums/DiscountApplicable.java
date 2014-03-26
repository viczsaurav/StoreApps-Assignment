package com.iss.storeApplication.enums;

/**
 * 
 * @author milan
 *
 */
public enum DiscountApplicable {

	Member("M"), All("A");
	/**
	 * @param text
	 */
	private DiscountApplicable(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}
}

package com.iss.storeApplication.enums;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;

/**
 * 
 * @author milan
 *
 */
public enum DiscountType {

	SEASONAL_DISCOUNT(Utility.getPropertyValue(Constants.seasonalDiscount)),PERMANENT_DISCOUNT(Utility.getPropertyValue(Constants.permanentDiscount));
	
	/**
	 * @param text
	 */
	private DiscountType(final String text) {
		this.text = text;
	}

	private final String text;

	@Override
	public String toString() {
		return text;
	}
}

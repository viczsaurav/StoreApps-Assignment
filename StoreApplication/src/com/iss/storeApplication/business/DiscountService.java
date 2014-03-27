package com.iss.storeApplication.business;

import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.dao.DiscountDao;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.SeasonalDiscount;

/**
 * 
 * @author milan
 *
 */
public class DiscountService {

	private static DiscountDao discountDao = new DiscountDao();

	/**
	 * It Saves Discount if valid
	 * 
	 * @param discount
	 * @return
	 */
	public static String validateAndSaveDiscount(Discount discount) {
		String msg = validateDiscount(discount);
		if (msg.equals(Constants.SUCCESS)) {
			if (discountDao.save(discount, true))
				return Constants.SUCCESS;
			else
				return Utility.getPropertyValue(Constants.failure);
		} else {
			return msg;
		}
	}

	/**
	 * It Validates Discount
	 * 
	 * @param discount
	 * @return
	 */
	public static String validateDiscount(Discount discount) {

		if (discount == null) {
			return Utility.getPropertyValue(Constants.validateMessage);
		}
		if (StringUtility.isEmpty(discount.getDescription())
				| StringUtility.isEmpty(discount.getDescription())
				| StringUtility.isEmpty(discount.getDiscountCode())
				| StringUtility.isEmpty(discount.getMemberApplicable())
				| discount.getDiscount() == null) {
			return Utility.getPropertyValue(Constants.validateMessage);
		}
		if (discount instanceof SeasonalDiscount) {
			SeasonalDiscount sd = (SeasonalDiscount) discount;
			if (sd.getDuration() == null | sd.getStartDate() == null) {
				return Utility.getPropertyValue(Constants.validateMessage);
			}

		}
		return Constants.SUCCESS;
	}

	/**
	 * get all discount stored in file
	 * 
	 * @return
	 */
	public static List<Discount> getDiscounts() {
		return discountDao.retrieveAll();
	}

	/**
	 * Checks if discount Code already exist in file
	 * 
	 * @param discountCode
	 * @return
	 */
	public static boolean isDiscountCodeAlreadyExist(String discountCode) {
		Map<String, Discount> map = discountDao.getMap();
		return map.containsKey(discountCode.trim());
	}

	/**
	 * Clears and Saves discount objects to file
	 * 
	 * @param discounts
	 * @return
	 */
	public static boolean saveAll(List<Discount> discounts) {

		return discountDao.saveAll(discounts);
	}
}

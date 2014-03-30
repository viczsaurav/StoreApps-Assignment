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
			return Utility.getPropertyValue(Constants.validateEmptyMessage);
		}
		if (StringUtility.isEmpty(discount.getDescription())
				| StringUtility.isEmpty(discount.getDescription())
				| StringUtility.isEmpty(discount.getDiscountCode())

				| discount.getDiscount() == null) {
			return Utility.getPropertyValue(Constants.validateEmptyMessage);
		}
		if(discount.getMemberApplicable()==null)
		{
			return Utility.getPropertyValue(Constants.validateEmptyMessage);
		}
		if (discount instanceof SeasonalDiscount) {
			SeasonalDiscount sd = (SeasonalDiscount) discount;
			if (sd.getDuration() == null | sd.getStartDate() == null) {
				return Utility.getPropertyValue(Constants.validateEmptyMessage);
			}
			if (sd.getDuration() < 0) {
				return Utility.getPropertyValue(Constants.msgnotNegative);
			}

		}

		if (discount.getDiscount() > 100) {
			return Utility.getPropertyValue(Constants.msgnotGrtThan100);
		}
		if (discount.getDiscount() < 0) { // not -ve
			return Utility.getPropertyValue(Constants.msgnotNegative);
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
	
	public static Discount getMaxPublicDiscount()
	{
		return discountDao.getMaxPublicDiscount();
	}
	
	public static Discount getMaxMemberDiscount()
	{
		return discountDao.getMaxMemberDiscount();
	}
	
	public static Discount getMemberFirstDiscount(){
		return discountDao.getMemberFirstDiscount();
	}
}

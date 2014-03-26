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

	public static String validateAndSaveDiscount(Discount discount) {
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
		if (discountDao.save(discount, true))
			return Constants.SUCCESS;
		else
			return Constants.failure;
	}

	public static List<Discount> getDiscounts() {
		return discountDao.retrieveAll();
	}

	public static boolean isDiscountCodeAlreadyExist(String discountCode) {
		Map<String, Discount> map = discountDao.getMap();
		return map.containsKey(discountCode.trim());
	}

	public static boolean saveAll(List<Discount> discounts) {

		return discountDao.saveAll(discounts);
	}
}

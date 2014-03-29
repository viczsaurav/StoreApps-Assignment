package com.iss.storeApplication.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iss.storeApplication.business.DiscountService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.PermanentDiscount;
import com.iss.storeApplication.domain.SeasonalDiscount;
import com.iss.storeApplication.enums.DiscountApplicable;

/**
 * 
 * @author milan
 *
 */
public class DiscountServiceTest {

	private static String fileName = Constants.FILENAME_DISCOUNT
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;
	private static List<Discount> discounts = new ArrayList<Discount>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// test 1
		SeasonalDiscount sd = new SeasonalDiscount();
		sd.setDescription("sd 1");
		sd.setDiscount(20.00);
		sd.setDiscountCode("sd code 1");
		sd.setDuration(10);
		sd.setStartDate(new Date());
		sd.setMemberApplicable(DiscountApplicable.Member);

		PermanentDiscount pd = new PermanentDiscount();
		pd.setDescription("pd 1");
		pd.setDiscount(30.0);
		pd.setDiscountCode("pd code 2");
		pd.setMemberApplicable(DiscountApplicable.Member);

		discounts.add(sd);
		discounts.add(pd);

		// test 2 : Discount code not greater than 100
		sd = new SeasonalDiscount();
		sd.setDescription("sd 1");
		sd.setDiscount(101.00);
		sd.setDiscountCode("sd code 1");
		sd.setDuration(10);
		sd.setStartDate(new Date());
		sd.setMemberApplicable(DiscountApplicable.Member);
		discounts.add(sd);

		// test 3 : Discount code / duration non -ve
		sd = new SeasonalDiscount();
		sd.setDescription("sd 1");
		sd.setDiscount(-1.00);
		sd.setDiscountCode("sd code 1");
		sd.setDuration(1);
		sd.setStartDate(new Date());
		sd.setMemberApplicable(DiscountApplicable.Member);
		discounts.add(sd);

		sd = new SeasonalDiscount();
		sd.setDescription("sd 1");
		sd.setDiscount(1.00);
		sd.setDiscountCode("sd code 1");
		sd.setDuration(-1);
		sd.setStartDate(new Date());
		sd.setMemberApplicable(DiscountApplicable.Member);
		discounts.add(sd);

		sd = new SeasonalDiscount();
		sd.setDescription("sd 1");
		sd.setDiscount(-1.00);
		sd.setDiscountCode("sd code 1");
		sd.setDuration(-1);
		sd.setStartDate(new Date());
		sd.setMemberApplicable(DiscountApplicable.Member);
		discounts.add(sd);
		
		// test 4 : discount object Null or string empty validation
		sd = new SeasonalDiscount();
		sd.setDescription("");
		sd.setDiscount(-1.00);
		sd.setDiscountCode("sd code 1");
		sd.setDuration(-1);
		sd.setStartDate(new Date());
		sd.setMemberApplicable(DiscountApplicable.Member);
		discounts.add(sd);
		
		sd = new SeasonalDiscount();
		sd.setDescription(null);
		sd.setDiscount(null);
		sd.setDiscountCode(null);
		sd.setDuration(null);
		sd.setStartDate(null);
		sd.setMemberApplicable(null);
		discounts.add(sd);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

		discounts.clear();
	}

	@Test
	public void test() {

		// Add Discount. We have done some validation in UI View itself like
		// date validation.

		// 1. normal seasonal discount validate and save
		assertEquals(DiscountService.validateDiscount(discounts.get(0)),
				Constants.SUCCESS);
		assertEquals(DiscountService.validateDiscount(discounts.get(1)),
				Constants.SUCCESS);

		// 2. Discount code not greater than 100
		assertEquals(DiscountService.validateDiscount(discounts.get(2)),
				Utility.getPropertyValue(Constants.msgnotGrtThan100));

		// 3. Discount code / duration non -ve
		assertEquals(DiscountService.validateDiscount(discounts.get(3)),
				Utility.getPropertyValue(Constants.msgnotNegative));
		assertEquals(DiscountService.validateDiscount(discounts.get(4)),
				Utility.getPropertyValue(Constants.msgnotNegative));
		assertEquals(DiscountService.validateDiscount(discounts.get(5)),
				Utility.getPropertyValue(Constants.msgnotNegative));
		
		// test 4 : discount object Null or string empty validation
		assertEquals(DiscountService.validateDiscount(null),
				Utility.getPropertyValue(Constants.validateEmptyMessage));
		assertEquals(DiscountService.validateDiscount(discounts.get(6)),
				Utility.getPropertyValue(Constants.validateEmptyMessage));
		assertEquals(DiscountService.validateDiscount(discounts.get(7)),
				Utility.getPropertyValue(Constants.validateEmptyMessage));
	}

}

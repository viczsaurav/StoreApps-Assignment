package com.iss.storeApplication.test;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.iss.storeApplication.business.DiscountService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.component.DatePicker;
import com.iss.storeApplication.controller.Controller;

public class ReportServiceTest {
	private final DatePicker startDate = new DatePicker("startDate", false);
	private final DatePicker endDate = new DatePicker("endDate", false);
	String startDt;
	String endDt;
	Date todayDate = new Date();
	int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	String prevDate = dateFormat.format(todayDate.getTime() - MILLIS_IN_DAY);
	String currDate = dateFormat.format(todayDate.getTime());
	String nextDate = dateFormat.format(todayDate.getTime() + MILLIS_IN_DAY);

	@Test
	public void test() {

		startDate.setDate(StringUtility.getDateFromString(currDate));
		endDate.setDate(StringUtility.getDateFromString(currDate));
		assertEquals(Controller.validateTimePeriod(startDate, endDate), true);

		startDate.setDate(StringUtility.getDateFromString(prevDate));
		endDate.setDate(StringUtility.getDateFromString(prevDate));
		assertEquals(Controller.validateTimePeriod(startDate, endDate), true);

		startDate.setDate(StringUtility.getDateFromString(prevDate));
		endDate.setDate(StringUtility.getDateFromString(currDate));
		assertEquals(Controller.validateTimePeriod(startDate, endDate), true);

		startDate.setDate(StringUtility.getDateFromString(prevDate));
		endDate.setDate(StringUtility.getDateFromString(nextDate));
		assertEquals(Controller.validateTimePeriod(startDate, endDate), true);

		startDate.setDate(StringUtility.getDateFromString(nextDate));
		endDate.setDate(StringUtility.getDateFromString(currDate));
		assertEquals(Controller.validateTimePeriod(startDate, endDate), false);

	}

}

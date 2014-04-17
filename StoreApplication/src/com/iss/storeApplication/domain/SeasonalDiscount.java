package com.iss.storeApplication.domain;

import java.util.Date;

import com.iss.storeApplication.common.StringUtility;

public class SeasonalDiscount extends Discount {

	private Date startDate;
	private Integer duration;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getCommaSeperatedValue() {
		return getDiscountCode() + "," + getDescription() + ","
				+ StringUtility.getStringFromDate(startDate) + "," + duration
				+ "," + getDiscount() + "," + getMemberApplicable();
	}

}

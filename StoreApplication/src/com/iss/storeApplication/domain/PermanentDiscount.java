package com.iss.storeApplication.domain;

import com.iss.storeApplication.common.Constants;

public class PermanentDiscount extends Discount {

	String startDate = Constants.ALWAYS;
	String duration = Constants.ALWAYS;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}

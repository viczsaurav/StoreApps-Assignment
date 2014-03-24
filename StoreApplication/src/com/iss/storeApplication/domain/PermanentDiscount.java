package com.iss.storeApplication.domain;

import com.iss.storeApplication.common.Constansts;

public class PermanentDiscount extends Discount {

	String startDate = Constansts.ALWAYS;
	String duration = Constansts.ALWAYS;

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

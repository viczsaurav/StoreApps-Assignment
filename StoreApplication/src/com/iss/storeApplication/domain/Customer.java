package com.iss.storeApplication.domain;

import com.iss.storeApplication.common.Constants;

public abstract class Customer {

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getLoyality() {
		return loyality;
	}

	public void setLoyality(Integer loyality) {
		this.loyality = loyality;
	}

	private String memberName=Constants.PUBLIC;

	private Integer loyality=0;
}

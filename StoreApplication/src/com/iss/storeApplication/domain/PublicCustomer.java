package com.iss.storeApplication.domain;

import com.iss.storeApplication.common.Constants;

public class PublicCustomer extends Customer {

	// not null and unique
	private String memberId = Constants.PUBLIC;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}

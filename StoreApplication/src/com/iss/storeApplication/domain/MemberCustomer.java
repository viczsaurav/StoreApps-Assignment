package com.iss.storeApplication.domain;

public class MemberCustomer extends Customer {
	// not null and unique
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getCommaSeperatedValue() {
		return  getMemberName() +","+memberId + ","
				+ getLoyality();
	}
	
	
}

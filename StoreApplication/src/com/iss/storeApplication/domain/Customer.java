package com.iss.storeApplication.domain;

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
	
	private String memberName;
	
	private Integer loyality;
}

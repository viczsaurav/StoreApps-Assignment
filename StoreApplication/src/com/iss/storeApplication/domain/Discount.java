package com.iss.storeApplication.domain;

public abstract class Discount {

	private String discountCode;
	private String description;
	private Double discount;// in %
	private String memberApplicable;

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getMemberApplicable() {
		return memberApplicable;
	}

	public void setMemberApplicable(String memberApplicable) {
		this.memberApplicable = memberApplicable;
	}

}

package com.iss.storeApplication.domain;

import com.iss.storeApplication.enums.DiscountApplicable;

public abstract class Discount implements Comparable<Discount> {

	private String discountCode;
	private String description;
	private Double discount;// in %
	private DiscountApplicable memberApplicable;

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

	public DiscountApplicable getMemberApplicable() {
		return memberApplicable;
	}

	public void setMemberApplicable(DiscountApplicable memberApplicable) {
		this.memberApplicable = memberApplicable;
	}

	@Override
	public int compareTo(Discount o) {

		return this.getDiscount().compareTo(o.getDiscount());
	}

}

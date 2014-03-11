package com.iss.storeApplication.domain;

import java.util.List;

public class Category {

	private String categoryCode;
	private String categoryName;
	private List<Vendor> vendors;
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<Vendor> getVendors() {
		return vendors;
	}
	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}
	
}

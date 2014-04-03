package com.iss.storeApplication.domain;

import java.util.List;

public class Vendor {

	private String name;
	private String description;
	private List<Category> categories;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommaSeperatedValue() {
		// TODO Auto-generated method stub
		return name + "," + description;
	}

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendor other = (Vendor) obj;

		if (other.getName().trim().equalsIgnoreCase(this.getName().trim())
				&& other.getDescription().equalsIgnoreCase(
						this.getDescription().trim())) {
			return true;
		} else {
			return false;
		}
	}

}

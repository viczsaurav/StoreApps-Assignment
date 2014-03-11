package com.iss.storeApplication.domain;

import java.util.Date;

public class PurchaseOrder {

	Integer orderedQty;
	Date orderDate;
	Product product;
	public Integer getOrderedQty() {
		return orderedQty;
	}
	public void setOrderedQty(Integer orderedQty) {
		this.orderedQty = orderedQty;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}

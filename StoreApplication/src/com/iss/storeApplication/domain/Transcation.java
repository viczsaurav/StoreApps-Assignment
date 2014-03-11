package com.iss.storeApplication.domain;

import java.util.Date;

public class Transcation {
	
	private String transactionId;
	private Product product;
	private Customer customer;
	private Integer qtyPurchase;
	private Date dateOfPurchase;
	private Discount discount;//can be null
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getQtyPurchase() {
		return qtyPurchase;
	}
	public void setQtyPurchase(Integer qtyPurchase) {
		this.qtyPurchase = qtyPurchase;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public Discount getDiscount() {
		return discount;
	}
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}
	
	
}

package com.iss.storeApplication.domain;

import java.util.Date;

import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;

/**
 * 
 * @author milan
 *
 */
public class Transaction {

	private String transactionId;
	private Product product;
	private Customer customer;
	private Integer qtyPurchase = 0;
	private Date dateOfPurchase = new Date();

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

	public String getCommaSeperatedValue() {
		String customerId = "";
		if (customer instanceof MemberCustomer) {
			customerId = ((MemberCustomer) customer).getMemberId();
		} else if (customer instanceof MemberCustomer) {
			customerId = ((PublicCustomer) customer).getMemberId();
		}
		return transactionId + "," + product.getProductId() + "," + customerId
				+ "," + qtyPurchase + ","
				+ StringUtility.getStringFromDate(dateOfPurchase);
	}

}

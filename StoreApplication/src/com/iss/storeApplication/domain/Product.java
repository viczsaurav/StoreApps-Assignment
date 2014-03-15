package com.iss.storeApplication.domain;

public class Product implements java.io.Serializable{

	private String productId;
	private String productName;
	private String description;
	private Integer qtyAvailable;
	private Double price;
	private Long barCode;
	private Integer reorderQty;
	private Integer orderQty;
	private Category category;
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQtyAvailable() {
		return qtyAvailable;
	}
	public void setQtyAvailable(Integer qtyAvailable) {
		this.qtyAvailable = qtyAvailable;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getBarCode() {
		return barCode;
	}
	public void setBarCode(Long barCode) {
		this.barCode = barCode;
	}
	public Integer getReorderQty() {
		return reorderQty;
	}
	public void setReorderQty(Integer reorderQty) {
		this.reorderQty = reorderQty;
	}
	public Integer getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(Integer orderQty) {
		this.orderQty = orderQty;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
	
	
}

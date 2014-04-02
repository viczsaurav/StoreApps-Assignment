package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Product;


/**
 * Table Model used by Product Table
 * @author Saurav
 *
 */
public class ProductTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * we define index numbers for columns in table in order to return the correct
	 * value in getValue() method and update the correct value in setValue()
	 * method
	 */
	private static final int COLUMN_PRODUCT_ID = 0;
	private static final int COLUMN_PRODUCT_NAME = 1;
	private static final int COLUMN_PRODUCT_DESC = 2;
	private static final int COLUMN_PRODUCT_QUANTITY = 3;
	private static final int COLUMN_PRICE_LABEL = 4;
	private static final int COLUMN_BARCODE_LABEL = 5;
	private static final int COLUMN_REORDER_QUANT_LABEL = 6;
	private static final int COLUMN_ORDER_QUANT_LABEL = 7;
	
	/**
	 * the list contains discounts objects
	 */
	private List<Product> listProducts;

	/**
	 * names for column header in table
	 */
	private String[] columnNames;

	/**
	 * Creates new instance of the model
	 */
	public ProductTableModel() {
		// initializes discount list
		this.listProducts = new ArrayList<Product>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.productId),
				Utility.getPropertyValue(Constants.productName),
				Utility.getPropertyValue(Constants.productDescription),
				Utility.getPropertyValue(Constants.productQty),
				Utility.getPropertyValue(Constants.productPrice),
				Utility.getPropertyValue(Constants.barcode),
				Utility.getPropertyValue(Constants.productReorderThreshold),
				Utility.getPropertyValue(Constants.productOrderQty) };
	}
	
	// Return all Product Objects
	public List<Product> getListProducts() {
		return listProducts;
	}

	/**
	 * returns the number of columns in the table
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * returns name of a specified column in the table
	 */
	public String getColumnName(int column) {
		return columnNames[column];
	}
	

	/**
	 * returns the number of rows in the table
	 */
	@Override
	public int getRowCount() {
		return listProducts.size();
	}
	
	/**
	 * this method will be used by the table component to get value of a given
	 * cell at [row, column]
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object value = null;
		Product product = listProducts.get(rowIndex);
		
		switch (columnIndex) {
		case COLUMN_PRODUCT_ID:
			value = product.getProductId();
			break;
		case COLUMN_PRODUCT_NAME:
			value = product.getDescription();
			break;
		case COLUMN_PRODUCT_DESC:
			value = product.getDescription();
			break;
		case COLUMN_PRODUCT_QUANTITY:
			value = product.getQtyAvailable();
			break;
		case COLUMN_PRICE_LABEL:
			value = product.getPrice();
			break;

		case COLUMN_BARCODE_LABEL:
			value = product.getBarCode();
			break;
		case COLUMN_REORDER_QUANT_LABEL:
			value = product.getReorderQty();
			break;

		case COLUMN_ORDER_QUANT_LABEL:
			value = product.getOrderQty();
			break;
		}
		return value;
	}

	/**
	 * this method will be used by the table component when user edits a given
	 * cell at [row, column]. The corresponding product object will be updated.
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Product product = listProducts.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_PRODUCT_ID:
			product.setProductId(value.toString());
			break;
		case COLUMN_PRODUCT_NAME:
			product.setProductName(value.toString());
			break;
		case COLUMN_PRODUCT_DESC:
			product.setDescription(value.toString());
			break;
		case COLUMN_PRODUCT_QUANTITY:
			product.setOrderQty(Integer.parseInt(value.toString()));
			break;
		case COLUMN_PRICE_LABEL:
			product.setPrice(new Double(value.toString()));
			break;
		case COLUMN_BARCODE_LABEL:
			product.setBarCode(new Long(value.toString()));
			break;
		case COLUMN_REORDER_QUANT_LABEL:
			product.setReorderQty(Integer.parseInt(value.toString()));
			break;

		case COLUMN_ORDER_QUANT_LABEL:
			product.setOrderQty(Integer.parseInt(value.toString()));
			break;
		}
	}
	
	/**
	 * since every cell in the table is non-editable, we always return "False"
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	/**
	 * this method allows the program adds a empty discount, a new row will be
	 * appended to the table.
	 */
	public void addProduct(Product product) {
		this.listProducts.add(product);
	}

	/**
	 * this method allows the program to remove a selected row in the table, the
	 * corresponding discount object in the product list is removed also.
	 */
	public void removeProduct(int rowIndex) {
		this.listProducts.remove(rowIndex);
	}

	public void clear() {
		this.listProducts.clear();
	}

}

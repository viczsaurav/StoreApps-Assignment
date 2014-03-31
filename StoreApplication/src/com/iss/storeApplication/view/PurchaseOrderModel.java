package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.PurchaseOrder;

/**
 * 
 * @author milan
 *
 */
public class PurchaseOrderModel extends AbstractTableModel {

	/*
	 * we define index numbers for columns in table in order to return the
	 * correct value in getValue() method and update the correct value in
	 * setValue() method
	 */
	private static final int COLUMN_VENDOR_NAME = 0;
	private static final int COLUMN_PRODUCT_ID = 1;
	private static final int COLUMN_ORDER_QTY = 2;
	private static final int COLUMN_ORDER_DATE = 3;

	/**
	 * the list contains PurchaseOrder objects
	 */
	private List<PurchaseOrder> purchaseOrders;

	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	/**
	 * names for column header in table
	 */
	private String[] columnNames;

	/**
	 * Creates new instance of the model
	 */
	public PurchaseOrderModel() {
		// initializes discount list
		this.purchaseOrders = new ArrayList<PurchaseOrder>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.vendorName),
				Utility.getPropertyValue(Constants.productId),
				Utility.getPropertyValue(Constants.productOrderQty),
				Utility.getPropertyValue(Constants.purchaseOrderDate) };
	}

	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	/**
	 * returns the number of columns in the table
	 */
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
	public int getRowCount() {
		return purchaseOrders.size();
	}

	/**
	 * this method will be used by the table component to get value of a given
	 * cell at [row, column]
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;

		PurchaseOrder purchaseOrder = purchaseOrders.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_VENDOR_NAME:
			value = purchaseOrder.getVendor().getName();
			break;
		case COLUMN_PRODUCT_ID:
			value = purchaseOrder.getProduct().getProductId();
			break;
		case COLUMN_ORDER_QTY:
			value = purchaseOrder.getOrderedQty();
			break;
		case COLUMN_ORDER_DATE:
			value = StringUtility.getStringFromDate(purchaseOrder.getOrderDate());
			break;

		}

		return value;
	}

	/**
	 * since every cell in the table is non-editable, we return "False" always
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	/**
	 * this method allows the program adds a empty discount, a new row will be
	 * appended to the table.
	 */
	public void addPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrders.add(purchaseOrder);
	}

	/**
	 * this method allows the program removes a selected row in the table, the
	 * corresponding discount object in the discount list is removed also.
	 */
	public void removePurchaseOrder(int rowIndex) {
		this.purchaseOrders.remove(rowIndex);
	}

	public void clear() {
		this.purchaseOrders.clear();
	}
}

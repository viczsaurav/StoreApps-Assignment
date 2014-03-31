package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.PurchaseOrder;

/**
 * 
 * @author milan
 *
 */
public class PurchaseOrderProductTableModel extends AbstractTableModel {
	/*
	 * we define index numbers for columns in table in order to return the
	 * correct value in getValue() method and update the correct value in
	 * setValue() method
	 */
	private static final int COLUMN_PRODUCT_ID = 0;
	private static final int COLUMN_QTY_AVAILABLE = 1;
	private static final int COLUMN_ORDER_QTY = 2;
	private static final int COLUMN_THRESOLD_QTY = 3;

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
	public PurchaseOrderProductTableModel() {
		// initializes discount list
		this.purchaseOrders = new ArrayList<PurchaseOrder>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.productId),
				Utility.getPropertyValue(Constants.productQty),
				Utility.getPropertyValue(Constants.productOrderQty),
				Utility.getPropertyValue(Constants.productReorderThreshold), };
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
		case COLUMN_PRODUCT_ID:
			value = purchaseOrder.getProduct().getProductId();
			break;
		case COLUMN_QTY_AVAILABLE:
			value = purchaseOrder.getProduct().getQtyAvailable();
			break;
		case COLUMN_ORDER_QTY:
			value = purchaseOrder.getProduct().getOrderQty();
			break;
		case COLUMN_THRESOLD_QTY:
			value = purchaseOrder.getProduct().getReorderQty();
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

package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Transaction;

/**
 * Table Model used by Billing Jtable
 * 
 * @author milan
 *
 */
public class BillingTableModel extends AbstractTableModel {

	/*
	 * we define index numbers for columns in table in order to return the
	 * correct value in getValue() method and update the correct value in
	 * setValue() method
	 */
	private static final int COLUMN_PRODUCT_ID = 0;
	private static final int COLUMN_PRODUCT_DESCRIPTION = 1;
	private static final int COLUMN_AVAILABLE_QTY = 2;
	private static final int COLUMN_PRICE = 3;
	private static final int COLUMN_PURCHASED_QTY = 4;
	
	private BillingView billingView;

	/**
	 * the list contains transactions objects
	 */
	private List<Transaction> listTransactions;

	/**
	 * names for column header in table
	 */
	private String[] columnNames;

	/**
	 * Creates new instance of the model
	 */
	public BillingTableModel(BillingView billingView) {
		this.billingView=billingView;
		// initializes Transcation list
		this.listTransactions = new ArrayList<Transaction>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.productId),
				Utility.getPropertyValue(Constants.productDescription),
				Utility.getPropertyValue(Constants.productQty),
				Utility.getPropertyValue(Constants.productPrice),
				Utility.getPropertyValue(Constants.purchaseQty) };

	}

	public List<Transaction> getListtransactions() {
		return listTransactions;
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
		return listTransactions.size();
	}

	/**
	 * this method will be used by the table component to get value of a given
	 * cell at [row, column]
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;

		Transaction Transcation = listTransactions.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_PRODUCT_ID:
			value = Transcation.getProduct().getProductId();
			break;
		case COLUMN_PRODUCT_DESCRIPTION:
			value = Transcation.getProduct().getDescription();
			break;
		case COLUMN_PRICE:
			value = Transcation.getProduct().getPrice();
			break;
		case COLUMN_AVAILABLE_QTY:
			value = Transcation.getProduct().getQtyAvailable();
			break;

		case COLUMN_PURCHASED_QTY:
			value = Transcation.getQtyPurchase();
			break;
		}

		return value;
	}

	/**
	 * this method will be used by the table component when user edits a given
	 * cell at [row, column]. The corresponding Transcation object will be
	 * updated.
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Transaction transcation = listTransactions.get(rowIndex);

		switch (columnIndex) {

		case COLUMN_PURCHASED_QTY:
			String qtyPurchased = value.toString();
			if (StringUtility.isEmpty(qtyPurchased)) {
				setValueAt("0", rowIndex, columnIndex);
				return;
			}
			Integer i = 0;
			try {
				i = Integer.parseInt(qtyPurchased);
			} catch (NumberFormatException nfe) {
				setValueAt("0", rowIndex, columnIndex);
				return;
			}
			if (i < 0) {
				JOptionPane.showMessageDialog(null, Utility
						.getPropertyValue(Constants.purchasedQtyNotNegative));
				setValueAt("0", rowIndex, columnIndex);
				transcation.setQtyPurchase(0);
				return;
			}
			int qty=new Integer(value.toString());
			if(transcation.getProduct().getQtyAvailable() < new Integer(value.toString()))
			{
				JOptionPane.showMessageDialog(null, Utility
						.getPropertyValue(Constants.purchasedQtyNotGreaterThanAvailable));
				setValueAt("0", rowIndex, columnIndex);
				transcation.setQtyPurchase(0);
				return;
			}
			transcation.setQtyPurchase(qty);
			billingView.calculateTotal();
			break;
		}
	}

	/**
	 * since every cell in the table is non-editable, we return "False" always
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (COLUMN_PURCHASED_QTY == columnIndex) {
			return true;
		} else
			return false;
	}

	/**
	 * this method allows the program adds a empty Transcation, a new row will
	 * be appended to the table.
	 */
	public void addTranscation(Transaction Transcation) {
		this.listTransactions.add(Transcation);
	}

	/**
	 * this method allows the program removes a selected row in the table, the
	 * corresponding Transcation object in the Transcation list is removed also.
	 */
	public void removeTranscation(int rowIndex) {
		this.listTransactions.remove(rowIndex);
	}

	public void clear() {
		this.listTransactions.clear();
	}

}

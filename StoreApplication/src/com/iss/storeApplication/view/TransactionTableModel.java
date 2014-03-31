package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.PublicCustomer;
import com.iss.storeApplication.domain.Transaction;

/**
 * 
 * @author milan
 *
 */
public class TransactionTableModel extends AbstractTableModel {

	/*
	 * we define index numbers for columns in table in order to return the
	 * correct value in getValue() method and update the correct value in
	 * setValue() method
	 */
	private static final int COLUMN_TRANSACTION_ID = 0;
	private static final int COLUMN_PRODUCT_ID = 1;
	private static final int COLUMN_CUSTOMER_ID = 2;
	private static final int COLUMN_PURCHASED_QTY = 3;
	private static final int COLUMN_DATE_OF_PURCHASE = 4;

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
	public TransactionTableModel() {

		// initializes Transcation list
		this.listTransactions = new ArrayList<Transaction>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.transactionId),
				Utility.getPropertyValue(Constants.productId),
				Utility.getPropertyValue(Constants.memberId),
				Utility.getPropertyValue(Constants.purchaseQty),
				Utility.getPropertyValue(Constants.puchaseDate) };

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

		Transaction transcation = listTransactions.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_TRANSACTION_ID:
			value = transcation.getTransactionId();
			break;
		case COLUMN_PRODUCT_ID:
			value = transcation.getProduct().getProductId();
			break;
		case COLUMN_CUSTOMER_ID:
			Customer c = transcation.getCustomer();
			if (c instanceof MemberCustomer) {
				value = ((MemberCustomer) c).getMemberId();
			} else {
				value = ((PublicCustomer) c).getMemberId();
			}

			break;
		case COLUMN_PURCHASED_QTY:
			value = transcation.getQtyPurchase();
			break;

		case COLUMN_DATE_OF_PURCHASE:
			value = StringUtility.getStringFromDate(transcation.getDateOfPurchase());
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

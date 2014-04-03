package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Vendor;

/**
 * 
 * @author milan
 *
 */
public class VendorTableModel extends AbstractTableModel {

	/*
	 * we define index numbers for columns in table in order to return the
	 * correct value in getValue() method and update the correct value in
	 * setValue() method
	 */
	private static final int COLUMN_VENDOR_NAME = 0;
	private static final int COLUMN_VENDOR_DESC = 1;

	/**
	 * the list contains Vendor objects
	 */
	private List<Vendor> vendors;

	public void setVendors(List<Vendor> vendors) {
		this.vendors = vendors;
	}

	/**
	 * names for column header in table
	 */
	private String[] columnNames;

	/**
	 * Creates new instance of the model
	 */
	public VendorTableModel() {
		// initializes discount list
		this.vendors = new ArrayList<Vendor>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.vendorName),
				Utility.getPropertyValue(Constants.vendorDesc), };
	}

	public List<Vendor> getVendors() {
		return vendors;
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
		return vendors.size();
	}

	/**
	 * this method will be used by the table component to get value of a given
	 * cell at [row, column]
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;

		Vendor vendor = vendors.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_VENDOR_NAME:
			value = vendor.getName();
			break;
		case COLUMN_VENDOR_DESC:
			value = vendor.getDescription();
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
	public void addVendor(Vendor vendor) {
		this.vendors.add(vendor);
	}

	/**
	 * this method allows the program removes a selected row in the table, the
	 * corresponding discount object in the discount list is removed also.
	 */
	public void removeVendor(int rowIndex) {
		this.vendors.remove(rowIndex);
	}

	public void clear() {
		this.vendors.clear();
	}
}

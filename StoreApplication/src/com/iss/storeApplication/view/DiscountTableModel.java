package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.PermanentDiscount;
import com.iss.storeApplication.domain.SeasonalDiscount;
import com.iss.storeApplication.enums.DiscountApplicable;

/**
 * Table Model used by Discount Jtable
 * @author milan
 *
 */
public class DiscountTableModel extends AbstractTableModel {

	/*
	 * we define index numbers for columns in table in order to return the correct
	 * value in getValue() method and update the correct value in setValue()
	 * method
	 */
	private static final int COLUMN_DISCOUNT_CODE = 0;
	private static final int COLUMN_DESCRIPTION = 1;
	private static final int COLUMN_START_DATE = 2;
	private static final int COLUMN_DURATION = 3;
	private static final int COLUMN_DISCOUNT = 4;
	private static final int COLUMN_APPLICABLE = 5;

	/**
	 * the list contains discounts objects
	 */
	private List<Discount> listDiscounts;

	/**
	 * names for column header in table
	 */
	private String[] columnNames;

	/**
	 * Creates new instance of the model
	 */
	public DiscountTableModel() {
		// initializes discount list
		this.listDiscounts = new ArrayList<Discount>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.discountCode),
				Utility.getPropertyValue(Constants.Description),
				Utility.getPropertyValue(Constants.startDate),
				Utility.getPropertyValue(Constants.duration),
				Utility.getPropertyValue(Constants.discount),
				Utility.getPropertyValue(Constants.memberApplicable) };
	}

	public List<Discount> getListDiscounts() {
		return listDiscounts;
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
		return listDiscounts.size();
	}

	/**
	 * this method will be used by the table component to get value of a given
	 * cell at [row, column]
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;

		Discount discount = listDiscounts.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_DISCOUNT_CODE:
			value = discount.getDiscountCode();
			break;
		case COLUMN_DESCRIPTION:
			value = discount.getDescription();
			break;
		case COLUMN_START_DATE:
			if (discount instanceof SeasonalDiscount) {
				value = StringUtility
						.getStringFromDate(((SeasonalDiscount) discount)
								.getStartDate());
			} else {
				value = ((PermanentDiscount) discount).getStartDate();
			}
			break;
		case COLUMN_DURATION:
			if (discount instanceof SeasonalDiscount) {
				value = ((SeasonalDiscount) discount).getDuration();
			} else {
				value = ((PermanentDiscount) discount).getDuration();
			}
			break;
		case COLUMN_APPLICABLE:
			value = discount.getMemberApplicable();
			break;

		case COLUMN_DISCOUNT:
			value = discount.getDiscount();
			break;
		}

		return value;
	}

	/**
	 * this method will be used by the table component when user edits a given
	 * cell at [row, column]. The corresponding discount object will be updated.
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Discount discount = listDiscounts.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_DISCOUNT_CODE:
			discount.setDiscountCode(value.toString());
			break;
		case COLUMN_DESCRIPTION:
			discount.setDescription(value.toString());
			break;
		case COLUMN_START_DATE:
			if (discount instanceof SeasonalDiscount) {
				StringUtility.getStringFromDate(((SeasonalDiscount) discount)
						.getStartDate());

				((SeasonalDiscount) discount).setStartDate(StringUtility
						.getDateFromString(value.toString()));
			} else {
				// ((PermanentDiscount)
				// discount).setStartDate(value.toString());
			}
			break;
		case COLUMN_DURATION:
			if (discount instanceof SeasonalDiscount) {
				((SeasonalDiscount) discount).setDuration(new Integer(value
						.toString()));
			} else {
				// ((PermanentDiscount) discount).setDuration(new
				// Integer(value.toString()));
			}
			break;
		case COLUMN_APPLICABLE:
			discount.setMemberApplicable(DiscountApplicable.valueOf(value.toString()));
			break;

		case COLUMN_DISCOUNT:
			discount.setDiscount(new Double(value.toString()));
			break;
		}
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
	public void addDiscount(Discount discount) {
		this.listDiscounts.add(discount);
	}

	/**
	 * this method allows the program removes a selected row in the table, the
	 * corresponding discount object in the discount list is removed also.
	 */
	public void removeDiscount(int rowIndex) {
		this.listDiscounts.remove(rowIndex);
	}

	public void clear() {
		this.listDiscounts.clear();
	}
	
	

}

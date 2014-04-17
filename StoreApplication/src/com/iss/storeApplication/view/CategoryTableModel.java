package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Category;

/**
 * Category table Model used by Jtable.
 * @author Mani
 *
 */

public class CategoryTableModel extends AbstractTableModel {

	
	/*
	 * we define index numbers for columns in table in order to return the right
	 * value in getValue() method and update the right value in setValue()
	 * method
	 */
	private static final int COLUMN_CATEGORY_CODE = 0;
	private static final int COLUMN_CATEGORY_NAME = 1;


	/**
	 * the list contains category objects
	 */
	private List<Category> listCategories;
	
	/**
	 * names for column header in table
	 */
	private String[] columnNames;

	/**
	 * Creates new instance of the model
	 */
	public CategoryTableModel()
	{
		this.listCategories = new ArrayList<Category>();
		columnNames = new String[] {
						Utility.getPropertyValue(Constants.categoryCode),
						Utility.getPropertyValue(Constants.categoryName) };
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listCategories.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Object value = null;
		
		Category c= listCategories.get(row);		
		switch(column)
		{
		case COLUMN_CATEGORY_CODE:
			value = c.getCategoryCode();
		break;
		case COLUMN_CATEGORY_NAME:
			value = c.getCategoryName();
			break;
		}
		return value;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return columnNames[column];
	}

	/*
	 * Since the cell should be only read only, overriding the isCellEditable to false.
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}	
	
	/**
	 * Adds a category to the List of categories through the model 
	 * 
	 */
	public void addCategory(Category category) {
		this.listCategories.add(category);
	}

	/**
	 * this method allows the program removes a selected row in the table, the
	 * corresponding category object in the category list is removed also.
	 */
	public void removeCategory(int rowIndex) {
		this.listCategories.remove(rowIndex);
	}

	public void clear() {
		this.listCategories.clear();
	}

}

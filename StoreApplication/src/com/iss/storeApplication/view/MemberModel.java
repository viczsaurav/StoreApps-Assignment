package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.MemberCustomer;
public class MemberModel extends AbstractTableModel{

	/*
	 * we define index numbers for columns in table in order to return the
	 * correct value in getValue() method and update the correct value in
	 * setValue() method
	 */
	
	private static final int COLUMN_MEMBER_NAME = 0;
	private static final int COLUMN_MEMBER_ID = 1;
	private static final int COLUMN_LOYALITY = 2;
	
	/**
	 * the list contains PurchaseOrder objects
	 */
	private List<MemberCustomer> members;
	/**
	 * names for column header in table
	 */
	private String[] columnNames;
	
	public List<MemberCustomer> getMembers() {
		return members;
	}

	public void setMembers(List<MemberCustomer> members) {
		this.members = members;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	/**
	 * Creates new instance of the model
	 */
	public MemberModel() {
		// initializes discount list
		this.members = new ArrayList<MemberCustomer>();
		// define column names
		columnNames = new String[] {
				Utility.getPropertyValue(Constants.memberId),
				Utility.getPropertyValue(Constants.memberName),
				Utility.getPropertyValue(Constants.loyality)};
	}
	@Override
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return members.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	/**
	 * this method will be used by the table component to get value of a given
	 * cell at [row, column]
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;

		MemberCustomer member= members.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_MEMBER_NAME:
			value = member.getMemberName();
			break;
		case COLUMN_MEMBER_ID:
			value = member.getMemberId();
			break;
		case COLUMN_LOYALITY:
			value = member.getLoyality();
			break;
		}

		return value;
	}
	
	/**
	 * this method will be used by the table component when user edits a given
	 * cell at [row, column]. The corresponding member customer object will be updated.
	 */
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		MemberCustomer member= members.get(rowIndex);

		switch (columnIndex) {
		case COLUMN_MEMBER_NAME:
			member.setMemberName(value.toString());
			break;
		case COLUMN_MEMBER_ID:
			member.setMemberId(value.toString());
			break;
		case COLUMN_LOYALITY:
			member.setLoyality((int)value);
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
	 * this method allows the program adds a empty membercustomer, a new row will be
	 * appended to the table.
	 */
	public void addMember(MemberCustomer memberCustomer) {
		this.members.add(memberCustomer);
	}

	/**
	 * this method allows the program removes a selected row in the table, the
	 * corresponding membercustomer object in the membercustomer list is removed also.
	 */
	public void removeMember(int rowIndex) {
		this.members.remove(rowIndex);
	}

	public void clear() {
		this.members.clear();
	}

}

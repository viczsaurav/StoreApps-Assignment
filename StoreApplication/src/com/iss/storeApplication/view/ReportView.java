package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.component.DatePicker;

/**
 * 
 * @author milan
 *
 */
public class ReportView extends JPanel{

	private final JButton categoryRptBtn = new JButton(Utility.getPropertyValue(Constants.CATEGORY_REPORT_BUTTON));
	private final JButton productRptBtn = new JButton(Utility.getPropertyValue(Constants.PRODUCT_REPORT_BUTTON));
	private final JButton transactionRptBtn = new JButton(Utility.getPropertyValue(Constants.TRANSACTION_REPORT_BUTTON));
	private final JButton memberRptBtn = new JButton(Utility.getPropertyValue(Constants.MEMBER_REPORT_BUTTON));

	private final JTable commonTable = new JTable();
	private final TableModel tm =new TableModel() {
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private MainView mainView;

	public ReportView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;
	JPanel panelButton = new JPanel();
	panelButton.add(categoryRptBtn, BorderLayout.NORTH);
	panelButton.add(productRptBtn, BorderLayout.NORTH);
	panelButton.add(transactionRptBtn, BorderLayout.NORTH);
	panelButton.add(memberRptBtn, BorderLayout.NORTH);
	add(panelButton, BorderLayout.NORTH);
	

	commonTable.setModel(tm);
	add(new JScrollPane(commonTable), BorderLayout.CENTER);
	categoryRptBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			bindCategories();
		}
	});
	
	}
	
	private void bindCategories()
	{
	final JButton getTransRptbtn = new JButton(Utility.getPropertyValue(Constants.GENERATE_TRANSACTIONS_BUTTON));
	final JLabel dateRange = new JLabel(Utility.getPropertyValue(Constants.startDate));
	final DatePicker startDate = new DatePicker();

	
	
	}
	
	
}

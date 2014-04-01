package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.component.DatePicker;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.Transaction;

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
	
	
	
	private final JButton getTransRptbtn = new JButton(Utility.getPropertyValue(Constants.GENERATE_TRANSACTIONS_BUTTON));
	private final DatePicker startDate = new DatePicker("startDate",false);
	private final DatePicker endDate = new DatePicker("endDate",false);

	private final JTable commonTable = new JTable();
	
	
	private MainView mainView;
	private CategoryTableModel categoryModel = new CategoryTableModel();
	private TransactionReportTableModel transactionReportModel = new TransactionReportTableModel();
	private ProductTableModel productModel = new ProductTableModel();

	private JPanel panelTransDatepicker = new JPanel();

	public ReportView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;
	JPanel panelButton = new JPanel();
	panelButton.add(categoryRptBtn, BorderLayout.NORTH);
	panelButton.add(productRptBtn, BorderLayout.NORTH);
	panelButton.add(transactionRptBtn, BorderLayout.NORTH);
	panelButton.add(memberRptBtn, BorderLayout.NORTH);
	add(panelButton, BorderLayout.NORTH);
	add(new JScrollPane(commonTable), BorderLayout.CENTER);
	

	//panelTransDatepicker.add(lblDateRangefrom,BorderLayout.NORTH);
	panelTransDatepicker.add(startDate,BorderLayout.NORTH);
	panelTransDatepicker.add(getTransRptbtn,BorderLayout.NORTH);
	//panelTransDatepicker.add(lblDateRangeto,BorderLayout.NORTH);
	panelTransDatepicker.add(endDate,BorderLayout.NORTH);
	
	add(panelTransDatepicker,BorderLayout.SOUTH);
	
	getTransRptbtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(validateTimePeriod())
			{
				
			}
			else
			{
				getTransactionBetweenDates();
			}
		}
	});
	
	categoryRptBtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent event) {
			refreshCategoryReport();		
		}
	});
	transactionRptBtn.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			intTransactionReport();
		}
	});
	
	}
	
	
	
	protected void intTransactionReport() {
		// TODO Auto-generated method stub
		
		commonTable.setModel(transactionReportModel);		
		transactionReportModel.clear();
		JOptionPane.showMessageDialog(mainView,Utility.getPropertyValue(Constants.selectDateFromBelow));
	}
	
	protected void getTransactionBetweenDates()
	{
		commonTable.setModel(transactionReportModel);		
		transactionReportModel.clear();
		List<Transaction> Transactions = Controller.getTransactions();
		for (Transaction t : Transactions) {
			if(t.getDateOfPurchase().compareTo(startDate.getPickedDate())>=0 && t.getDateOfPurchase().compareTo(endDate.getPickedDate())<=0 )
			{
			transactionReportModel.addTranscation(t);
			transactionReportModel.fireTableDataChanged();
			}
	}
		add(panelTransDatepicker,BorderLayout.SOUTH);
		SwingUtility.refreshJpanel(this);
	}

	protected boolean validateTimePeriod() {
		// TODO Auto-generated method stub
		if(this.endDate.getPickedDate().compareTo(this.startDate.getPickedDate())<0)
		{
			JOptionPane.showMessageDialog(mainView,Utility.getPropertyValue(Constants.dateIntervalInvalid));
			endDate.setDate(startDate.getPickedDate());
			return false;
		}
		else
		{
			return true;
		}
	}

	
	private void bindTransactions()
	{
		
		//bindCategories();
	}
	
	public void refreshCategoryReport()
	{
		commonTable.setModel(categoryModel);		
		categoryModel.clear();
		List<Category> Categories = Controller.getCategories();
		for (Category c : Categories) {
			categoryModel.addCategory(c);
		categoryModel.fireTableDataChanged();
		remove(panelTransDatepicker);
		SwingUtility.refreshJpanel(this);
	}
}
}

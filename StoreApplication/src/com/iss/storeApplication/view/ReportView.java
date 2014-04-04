	package com.iss.storeApplication.view;
	
	import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.component.DatePicker;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.Transaction;
	
	/**
	 * 
	 * @author milan
	 * 
	 */
	public class ReportView extends JPanel {
	
		private final JButton categoryRptBtn = new JButton(
				Utility.getPropertyValue(Constants.CATEGORY_REPORT_BUTTON));
		private final JButton productRptBtn = new JButton(
				Utility.getPropertyValue(Constants.PRODUCT_REPORT_BUTTON));
		private final JButton transactionRptBtn = new JButton(
				Utility.getPropertyValue(Constants.TRANSACTION_REPORT_BUTTON));
		private final JButton memberRptBtn = new JButton(
				Utility.getPropertyValue(Constants.MEMBER_REPORT_BUTTON));
	
		private final JButton getTransRptbtn = new JButton(
				Utility.getPropertyValue(Constants.GENERATE_TRANSACTIONS_BUTTON));
		private final DatePicker startDate = new DatePicker("startDate", false);
		private final DatePicker endDate = new DatePicker("endDate", false);
	
		private final JTable commonTable = new JTable();
	
		private MainView mainView;
		private CategoryTableModel categoryModel = new CategoryTableModel();
		private TransactionReportTableModel transactionReportModel = new TransactionReportTableModel();
		private ProductTableModel productModel = new ProductTableModel();
		private MemberModel memberModel = new MemberModel();
	
		private JPanel panelTransDatepicker = new JPanel();
	
		public ReportView(MainView mainView) {
			super(new BorderLayout());
			this.mainView = mainView;
			intReportView();
			getTransRptbtn.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					if (Controller.validateTimePeriod(startDate,endDate)) {
						getTransactionBetweenDates();
					}
					else
					{
						resetDateRange();
					}
				}
			});
	
			categoryRptBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					refreshCategoryReport();
				}
			});
	
			productRptBtn.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					refreshProductReport();
				}
	
			});
			transactionRptBtn.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					intTransactionReport();
				}
			});
			memberRptBtn.addActionListener(new ActionListener() {
				
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					refreshMemberReport();
					
				}
			});
	
		}
	
		protected void resetDateRange() {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(mainView,Utility.getPropertyValue(Constants.dateIntervalInvalid));
			endDate.setDate(startDate.getPickedDate());
			
		}

		protected void refreshMemberReport() {
			// TODO Auto-generated method stub
			commonTable.setModel(memberModel);
			memberModel.clear();
			panelTransDatepicker.setVisible(false);
			List<Customer> memberCustomers = Controller.getMemberCustomers();
			for(Customer mc:memberCustomers)
			{
				memberModel.addMember((MemberCustomer) mc);
				memberModel.fireTableDataChanged();
			}
		}

		protected void refreshProductReport() {
			// TODO Auto-generated method stub
			commonTable.setModel(productModel);
			productModel.clear();
			List<Product> Products = Controller.getProducts();
			for (Product p : Products) {
				productModel.addProduct(p);
				productModel.fireTableDataChanged();
			}
			panelTransDatepicker.setVisible(false);
			SwingUtility.refreshJpanel(this);
		}
	
		protected void intReportView() {
			
			JPanel panelButton = new JPanel();
			panelButton.add(categoryRptBtn, BorderLayout.NORTH);
			panelButton.add(productRptBtn, BorderLayout.NORTH);
			panelButton.add(transactionRptBtn, BorderLayout.NORTH);
			panelButton.add(memberRptBtn, BorderLayout.NORTH);
			add(panelButton, BorderLayout.NORTH);
			add(new JScrollPane(commonTable), BorderLayout.CENTER);
			Date todayDate = new Date();
			String dateString = StringUtility.getStringFromDate(todayDate);
			startDate.setDate(StringUtility.getDateFromString(dateString));
			endDate.setDate(StringUtility.getDateFromString(dateString));
			panelTransDatepicker.add(startDate, BorderLayout.NORTH);
			panelTransDatepicker.add(getTransRptbtn, BorderLayout.NORTH);
			panelTransDatepicker.add(endDate, BorderLayout.NORTH);
			add(panelTransDatepicker, BorderLayout.SOUTH);
			panelTransDatepicker.setVisible(false);
			SwingUtility.refreshJpanel(this);
		}
	
		protected void intTransactionReport() {
			// TODO Auto-generated method stub
			
			commonTable.setModel(transactionReportModel);
			transactionReportModel.clear();
			List<Transaction> Transactions = Controller.getTodayTransactions();
			for(Transaction t: Transactions)
			{
				transactionReportModel.addTranscation(t);
				transactionReportModel.fireTableDataChanged();
			}
			
			panelTransDatepicker.setVisible(true);
		}
	
		protected void getTransactionBetweenDates() {
			commonTable.setModel(transactionReportModel);
			transactionReportModel.clear();
			List<Transaction> sortedTransactions = Controller.sortTransactions(startDate,endDate);
			for (Transaction t : sortedTransactions) {
				transactionReportModel.addTranscation(t);
				transactionReportModel.fireTableDataChanged();
			}
			SwingUtility.refreshJpanel(this);
		}
	
	
		public void refreshCategoryReport() {
			commonTable.setModel(categoryModel);
			categoryModel.clear();
			List<Category> Categories = Controller.getCategories();
			for (Category c : Categories) {
				categoryModel.addCategory(c);
				categoryModel.fireTableDataChanged();
				panelTransDatepicker.setVisible(false);
				SwingUtility.refreshJpanel(this);
			}
		}
	}

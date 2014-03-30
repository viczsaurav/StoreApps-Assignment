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

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;

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
	

	commonTable.setModel(null);
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
	
	}
	
	
}

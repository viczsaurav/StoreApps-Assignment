package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.iss.storeApplication.common.Constants;

/**
 * 
 * @author milan
 *
 */
public class MainView extends JFrame {

	private static final Color gradientColor = new Color(107, 106, 104);
	private static final float gradientX = 1000;
	private static final float gradientY = 1000;

	private JPanel contentPanel = new JPanel();

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	private CardLayout cardLayout = new CardLayout();

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public MainView() {

		initMainView();

	}

	/**
	 * init main Frame
	 */
	private void initMainView() {

		//init main view
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setTitle("University Souvenir Store Application - SE224FT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add tool bar
		addDefaultToolbar();
		
		//add content panel
		addContentPanel();
	}	
	
	
	/**
	 *  set card layout in content panel. 
	 */
	private void addContentPanel() {
		contentPanel.setBackground(Color.CYAN);
		contentPanel.setSize(600, 600);
		//cardLayout.setHgap(10);
		//cardLayout.setVgap(10);
		
		//set card layout panel
		contentPanel.setLayout(cardLayout);

		//add all content views in content panel
		contentPanel.add(Constants.TRANSACTIONBUTTONLBL, new TransactionView());
		contentPanel.add(Constants.CATEGORYBUTTONLBL, new CategoryView());
		contentPanel.add(Constants.DISCOUNTBUTTONLBL, new DiscountView(this));
		contentPanel.add(Constants.MEMBERBUTTONLBL, new MemberView());
		contentPanel.add(Constants.PRODUCTBUTTONLBL, new ProductView(this));
		contentPanel.add(Constants.PURCHASEORDERBUTTONLBL,
				new PurchaseOrderView());
		contentPanel.add(Constants.REPORTBUTTONLBL, new ReportView());
		add(contentPanel);
	}

	/**
	 * 
	 * @author sakthi
	 * 
	 */

	public void showLoginView() {

		LoginPopupView.showLoginDialog(this);

	}
	
	/*
	 * add Tool bar
	 * 
	 */
	public void addDefaultToolbar() {
		ToolBarView toolBarView = new ToolBarView(this);
		add(toolBarView, BorderLayout.NORTH);
	}

}

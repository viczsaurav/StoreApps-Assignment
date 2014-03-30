package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;

/**
 * 
 * @author milan
 * 
 */
public class MainView extends JFrame {

	public static JPanel contentPanel = new JPanel();

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

		// init main view
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setTitle("University Souvenir Store Application - SE224FT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add tool bar
		addDefaultToolbar();

		// add content panel
		addContentPanel();
	}

	/**
	 * set card layout in content panel.
	 */
	private void addContentPanel() {

		TitledBorder nameBorder = BorderFactory
				.createTitledBorder("Transaction");
		contentPanel.setBorder(nameBorder);
		contentPanel
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPanel.setPreferredSize(new Dimension(550, 550));
		contentPanel.setMaximumSize(new Dimension(1100, 650));
		contentPanel.setMinimumSize(new Dimension(150, 150));
		contentPanel.setLayout(cardLayout);

		// set card layout panel

		contentPanel.setLayout(cardLayout);

		// add all content views in content panel
		contentPanel.add(Utility.getPropertyValue(Constants.billing), new BillingView(this));
		contentPanel.add(Utility.getPropertyValue(Constants.category), new CategoryView());
		contentPanel.add(Utility.getPropertyValue(Constants.discount), new DiscountView(this));
		contentPanel.add(Utility.getPropertyValue(Constants.members), new MemberView());
		contentPanel.add(Utility.getPropertyValue(Constants.products), new ProductView(this));
		contentPanel.add(Utility.getPropertyValue(Constants.purchaseOrder),
				new PurchaseOrderView());
		contentPanel.add(Utility.getPropertyValue(Constants.reports), new ReportView(this));
		contentPanel.add(Utility.getPropertyValue(Constants.logout), new LogoutView());
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
	 */
	public void addDefaultToolbar() {
		ToolBarView toolBarView = new ToolBarView(this);
		add(toolBarView, BorderLayout.NORTH);
		toolBarView.setFloatable(false);
	}

}

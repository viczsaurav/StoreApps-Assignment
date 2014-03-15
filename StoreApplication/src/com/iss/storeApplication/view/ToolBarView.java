package com.iss.storeApplication.view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
	/*
	 * author Saurav
	 */
public class ToolBarView extends JFrame
				implements	ActionListener	{
	
	private static JToolBar toolBar;
		
	// button names
	private static String category_button = "Category";
	private static String member_button = "Members";
	private static String discount_button = "Discount";
	private static String purchaseOrder_button = "Purchase Order";
	private static String product_button = "Products";
	private static String report_button = "Reports";
	private static String logout_button = "Logout";
	
	public ToolBarView(){
		
	}
	
	public static void addToolBar(MainView mainView) {
		
		try {
			mainView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				toolBar = new JToolBar();
				mainView.getContentPane().add(toolBar, BorderLayout.NORTH);
				toolBar.setPreferredSize(new java.awt.Dimension(390, 35));
				
				// Adding Buttons
				toolBar.add(addButton(category_button));
				toolBar.add(addButton(member_button));
				toolBar.add(addButton(discount_button));
				toolBar.add(addButton(purchaseOrder_button));
				toolBar.add(addButton(product_button));
				toolBar.add(addButton(report_button));
				toolBar.add(addButton(logout_button));
				
				// Packing buttons/toolbar in  the JFRame
				mainView.pack();
			} 
		}
		catch (Exception e) {
			    //add your error handling code here
				e.printStackTrace();
			}
	}
	
	public static JButton addButton(String buttonName){
		JButton button = new JButton();
		button.setText(buttonName);
		return button;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

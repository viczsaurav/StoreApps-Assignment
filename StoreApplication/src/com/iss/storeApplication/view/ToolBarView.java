package com.iss.storeApplication.view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
	/*
	 * @author Saurav
	 */
public class ToolBarView extends JToolBar
				implements	ActionListener	{
	
	// button names
	private static String category_button = "Category";
	private static String member_button = "Members";
	private static String discount_button = "Discount";
	private static String purchaseOrder_button = "Purchase Order";
	private static String product_button = "Products";
	private static String report_button = "Reports";
	private static String logout_button = "Logout";
	
	public ToolBarView(){
		addToolBar();
	}
	
	public void addToolBar() {
		
		try {
			//	Set Preferred Size
				setPreferredSize(new java.awt.Dimension(400, 40));
				
			// Adding Buttons
				add(addButton(category_button));
				add(addButton(member_button));
				add(addButton(discount_button));
				add(addButton(purchaseOrder_button));
				add(addButton(product_button));
				add(addButton(report_button));
				add(addButton(logout_button));
				
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

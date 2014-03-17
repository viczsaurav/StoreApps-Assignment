package com.iss.storeApplication.view;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.common.Utility;
	/*
	 * @author Saurav
	 */
public class ToolBarView extends JToolBar
				implements	ActionListener	{
	
	public ToolBarView(){
		addToolBar();
	}
	
	public void addToolBar() {
		
		try {
			// Adding Buttons
				add(addButton(Utility.getPropertyValue(Constansts.CATEGORYBUTTONLBL)));
				add(addButton(Utility.getPropertyValue(Constansts.MEMBERBUTTONLBL)));
				add(addButton(Utility.getPropertyValue(Constansts.DISCOUNTBUTTONLBL)));
				add(addButton(Utility.getPropertyValue(Constansts.PURCHASEORDERBUTTONLBL)));
				add(addButton(Utility.getPropertyValue(Constansts.PRODUCTBUTTONLBL)));
				add(addButton(Utility.getPropertyValue(Constansts.REPORTBUTTONLBL)));
				add(addButton(Utility.getPropertyValue(Constansts.LOGOUTBUTTONLBL)));
				
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

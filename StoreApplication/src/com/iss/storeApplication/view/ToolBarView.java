package com.iss.storeApplication.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import com.iss.storeApplication.common.Constants;

/*
 * @author Saurav
 */
public class ToolBarView extends JToolBar implements ActionListener {

	private MainView mainView;
	private static JLabel label;


	public ToolBarView(MainView mainView) {
		this.mainView=mainView;
		addToolBar();
	}

	public void addToolBar() {

		try {
			// Adding Buttons
			// add(addButton(Utility.getPropertyValue(Constants.CATEGORYBUTTONLBL)));
			add(addButton(Constants.TRANSACTIONBUTTONLBL));
			add(addButton(Constants.CATEGORYBUTTONLBL));
			add(addButton(Constants.MEMBERBUTTONLBL));
			add(addButton(Constants.DISCOUNTBUTTONLBL));
			add(addButton(Constants.PURCHASEORDERBUTTONLBL));
			add(addButton(Constants.PRODUCTBUTTONLBL));
			add(addButton(Constants.REPORTBUTTONLBL));
			add(addButton(Constants.LOGOUTBUTTONLBL));
			add(addLabel("Label"));

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}
	  
	/**
	 * @author sakthi
	 */
	public JLabel addLabel(final String  labelName)
	{		
		Dimension size = new Dimension(650, 20);		
		addSeparator(size);		
		label = new JLabel();		
		label.setVisible(false);		
		return label;
	}
	
	
	public  JButton addButton(final String buttonName) {
		JButton button = new JButton();
		button.setText(buttonName);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showContent(buttonName);
				
			}
		});
		return button;
	}

	

	public MainView getMainView() {
		return mainView;
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	protected  void showContent(String name) {
		mainView.getCardLayout().show(mainView.getContentPanel(), name);
		/**
		 * @author sakthi
		 */
		if(name.equals("Logout")){
			LoginPopupView.userNameTxtField.setText("");
		    LoginPopupView.passwordField.setText("");
		    label.setText("");
			label.setVisible(false);			    
			LoginPopupView.showLoginDialog(mainView);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	/**
	 * @author sakthi
	 */
	
	public static void loginWelcomeMessage()
	{		
		String userName = LoginPopupView.userNameTxtField.getText();
		label.setText("Welcome "+  userName);
		label.setVisible(true);				
	}
}

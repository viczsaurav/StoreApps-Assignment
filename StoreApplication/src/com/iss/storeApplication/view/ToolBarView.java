package com.iss.storeApplication.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import com.iss.storeApplication.common.Constants;

/*
 * @author Saurav
 */
public class ToolBarView extends JToolBar implements ActionListener {

	private MainView mainView;


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

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
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
		if(name.equals("Logout")){
			LoginPopupView.userNameTxtField.setText("");
		    LoginPopupView.passwordField.setText("");
			LoginPopupView.showLoginDialog(mainView);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

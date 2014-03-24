package com.iss.storeApplication.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import com.iss.storeApplication.common.Constansts;

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
			// add(addButton(Utility.getPropertyValue(Constansts.CATEGORYBUTTONLBL)));
			add(addButton(Constansts.CATEGORYBUTTONLBL));
			add(addButton(Constansts.MEMBERBUTTONLBL));
			add(addButton(Constansts.DISCOUNTBUTTONLBL));
			add(addButton(Constansts.PURCHASEORDERBUTTONLBL));
			add(addButton(Constansts.PRODUCTBUTTONLBL));
			add(addButton(Constansts.REPORTBUTTONLBL));
			add(addButton(Constansts.LOGOUTBUTTONLBL));

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}

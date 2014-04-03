package com.iss.storeApplication.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;

/*
 * @author Saurav
 */
public class ToolBarView extends JToolBar implements ActionListener {

	private MainView mainView;
	private static JLabel label;
	private static JButton button;
 public static String toolBarName="";
	public ToolBarView(MainView mainView) {
		this.mainView = mainView;
		addToolBar();
	}

	public void addToolBar() {

		try {
			// Adding Buttons
			// add(addButton(Utility.getPropertyValue(Constants.CATEGORYBUTTONLBL)));
			add(addButton(Utility.getPropertyValue(Constants.billing)));
			add(addButton(Utility.getPropertyValue(Constants.category)));
			add(addButton(Utility.getPropertyValue(Constants.members)));
			add(addButton(Utility.getPropertyValue(Constants.discount)));
			add(addButton(Utility.getPropertyValue(Constants.vendor)));
			add(addButton(Utility.getPropertyValue(Constants.purchaseOrder)));
			add(addButton(Utility.getPropertyValue(Constants.products)));
			add(addButton(Utility.getPropertyValue(Constants.reports)));
			add(addButton(Utility.getPropertyValue(Constants.logout)));
			add(addLabel("Label"));

		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	/**
	 * @author sakthi
	 */
	public JLabel addLabel(final String labelName) {
		addSeparator();
		label = new JLabel();
		label.setVisible(false);
		return label;
	}

	public JButton addButton(final String buttonName) {

		button = new JButton();
		button.setText(buttonName);
		setToolbarShortcut(buttonName);

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

	protected void showContent(String name) {
		toolBarName = "";
		TitledBorder nameBorder = BorderFactory.createTitledBorder(
				name);
		MainView.contentPanel.setBorder(nameBorder);
		mainView.getCardLayout().show(mainView.getContentPanel(), name);

		// refresh visible card panel
		JPanel card = null;
		for (Component comp : mainView.getContentPanel().getComponents()) {
			if (comp.isVisible() == true) {
				card = (JPanel) comp;
				card.repaint();
			}

		}

		/**
		 * @author sakthi
		 */

		if (name.equals(Utility.getPropertyValue(Constants.logout))) {
			LoginPopupView.userNameTxtField.setText("");
			LoginPopupView.passwordField.setText("");
			label.setText("");
			label.setVisible(false);
			toolBarName = Utility.getPropertyValue(Constants.logout);
			getToolbarName();
			LoginPopupView.showLoginDialog(mainView);
		}	
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	/**
	 * @author sakthi
	 */

	public static void loginWelcomeMessage() {
		String userName = LoginPopupView.userNameTxtField.getText();
		label.setText("Welcome " + userName + "!!!" +  "  ");
		label.setFont(new Font("Serif", Font.BOLD, 14));
		label.setVisible(true);
	}
	
	/**
	 * @author sakthi
	 */

	public static void setToolbarShortcut(String buttonName) {

		if (buttonName.equals(Utility.getPropertyValue(Constants.billing))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_B);
		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.category))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_C);

		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.members))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_M);

		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.vendor))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_I);

		}
		
		if (buttonName.equals(Utility.getPropertyValue(Constants.discount))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_D);

		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.purchaseOrder))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_U);

		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.products))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_P);

		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.reports))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_R);

		}
		if (buttonName.equals(Utility.getPropertyValue(Constants.logout))) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_L);

		}
	}
	
	
	public static String getToolbarName() {
		//toolBarName = name;
	   return toolBarName;
	}

}

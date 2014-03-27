package com.iss.storeApplication.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.iss.storeApplication.common.Constants;

/*
 * @author Saurav
 */
public class ToolBarView extends JToolBar implements ActionListener {

	private MainView mainView;
	private static JLabel label;
	private static JButton button;

	public ToolBarView(MainView mainView) {
		this.mainView = mainView;
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
	public JLabel addLabel(final String labelName) {
		Dimension size = new Dimension(650, 20);
		addSeparator(size);
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

		if (name.equals("Logout")) {
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

	public static void loginWelcomeMessage() {
		String userName = LoginPopupView.userNameTxtField.getText();
		label.setText("Welcome " + userName);
		label.setVisible(true);
	}
	
	/**
	 * @author sakthi
	 */

	public static void setToolbarShortcut(String buttonName) {

		if (buttonName.equals(Constants.TRANSACTIONBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_T);
		}
		if (buttonName.equals(Constants.CATEGORYBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_C);

		}
		if (buttonName.equals(Constants.MEMBERBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_M);

		}
		if (buttonName.equals(Constants.DISCOUNTBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_D);

		}
		if (buttonName.equals(Constants.PURCHASEORDERBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_U);

		}
		if (buttonName.equals(Constants.PRODUCTBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_P);

		}
		if (buttonName.equals(Constants.REPORTBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_R);

		}
		if (buttonName.equals(Constants.LOGOUTBUTTONLBL)) {
			button.setMnemonic(java.awt.event.KeyEvent.VK_L);

		}
	}

}

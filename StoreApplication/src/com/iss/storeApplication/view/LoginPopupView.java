package com.iss.storeApplication.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.StoreKeeper;
/*import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import javax.swing.JComponent;*/
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.RequestFocusListener;
import com.iss.storeApplication.common.Utility;

/**
 * 
 * @author sakthi
 * 
 */
public class LoginPopupView {

	private static JPanel loginPanel = new JPanel();
	public static JTextField userNameTxtField = new JTextField();
	public static JPasswordField passwordField = new JPasswordField();
	static String[] loginBtns = { Constants.login, Constants.cancel };

	
	static {
		loginPanel.setLayout(new GridLayout(2, 10));
		loginPanel.setBackground(new Color(255, 0, 0, 20));
		loginPanel.setOpaque(false);
		loginPanel.add(new JLabel(Constants.LOGIN_LABEL_TEXT_USERNAME));
		loginPanel.add(userNameTxtField);
		loginPanel.add(new JLabel(Constants.LOGIN_LABEL_TEXT_PASSWORD));
		loginPanel.add(passwordField);
	}

	

	public static boolean showLoginDialog(MainView mainView) {

		userNameTxtField.addAncestorListener(new RequestFocusListener());
		(userNameTxtField).requestFocusInWindow();

		int result = JOptionPane.showOptionDialog(mainView, loginPanel,
				Constants.LOGIN_DIALOG_TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, loginBtns, loginBtns[0]);
		if (result == JOptionPane.OK_OPTION) {
			char[] passwordChar = passwordField.getPassword();
			String passwordString = new String(passwordChar);
			String message = Controller.validateUser(new StoreKeeper(
					userNameTxtField.getText().trim(), passwordString.trim()));

			if (message.equals(Constants.LOGIN_SUCCESS_MESSAGE)) {
				mainView.getCardLayout().show(mainView.getContentPanel(), Utility.getPropertyValue(Constants.billing));
				TitledBorder nameBorder = BorderFactory
						.createTitledBorder(Utility.getPropertyValue((Constants.billing)));
				mainView.contentPanel.setBorder(nameBorder);
				ToolBarView.loginWelcomeMessage();				
				ToolBarView.getToolbarName();
				ToolBarView.toolBarName="";
				return true;

			} else {
				// show message to user
				JOptionPane.showMessageDialog(null, message, "Message",
						JOptionPane.ERROR_MESSAGE);				
				showLoginDialog(mainView);
				return false;
			}

		} else {

			mainView.dispatchEvent(new WindowEvent(mainView,
					WindowEvent.WINDOW_CLOSING));
			return false;
		}

	}

	

}

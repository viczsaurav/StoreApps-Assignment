package com.iss.storeApplication.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 *
 */
public class LoginPopupView {

	
	private static JPanel loginPanel = new JPanel();
	static JTextField userNameTxtField = new JTextField();
	static JPasswordField passwordField = new JPasswordField();
	static String[] loginBtns = { Constansts.LOGIN_BTN, Constansts.CANCEL };
	
	static{
		loginPanel.setLayout(new GridLayout(2, 3));
		loginPanel.setBackground(new Color(255, 0, 0, 20));
		loginPanel.setOpaque(false);
		loginPanel.add(new JLabel(Constansts.LOGIN_LABEL_TEXT_USERNAME));
		loginPanel.add(userNameTxtField);
		loginPanel.add(new JLabel(Constansts.LOGIN_LABEL_TEXT_PASSWORD));
		loginPanel.add(passwordField);
	}
	public static boolean showLoginDialog(MainView mainView) {
	
						
		int result = JOptionPane.showOptionDialog(mainView, loginPanel,
				Constansts.LOGIN_DIALOG_TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, loginBtns,
				loginBtns[0]);
		if (result == JOptionPane.OK_OPTION) {
			
			return Controller.validateUser(new StoreKeeper(userNameTxtField.getText().trim(), passwordField.getText().trim() ));
	        
		   	
		} 
		else {
			mainView.dispatchEvent(new WindowEvent(mainView, WindowEvent.WINDOW_CLOSING));
			return false;
		}

	}

}

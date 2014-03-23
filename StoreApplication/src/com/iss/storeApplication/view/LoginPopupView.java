package com.iss.storeApplication.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

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

	private static final Color gradientColor = new Color(107, 106, 104);
	private static final float gradientX = 1000;
	private static final float gradientY = 1000;

	static {
		loginPanel.setLayout(new GridLayout(2, 3));
		loginPanel.setBackground(new Color(255, 0, 0, 20));
		loginPanel.setOpaque(false);
		loginPanel.add(new JLabel(Constansts.LOGIN_LABEL_TEXT_USERNAME));
		loginPanel.add(userNameTxtField);
		loginPanel.add(new JLabel(Constansts.LOGIN_LABEL_TEXT_PASSWORD));
		loginPanel.add(passwordField);
	}

	private static JPanel mainPanel = new JPanel() {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Paint paint = new GradientPaint(0f, 0f, Color.white, gradientX,
					gradientY, gradientColor, true);
			Graphics2D graphics = (Graphics2D) g;
			graphics.setPaint(paint);
			graphics.fillRect(0, 0, getWidth(), getHeight());

		}
	};

	public static boolean showLoginDialog(MainView mainView) throws InstantiationException, IllegalAccessException {

		int result = JOptionPane.showOptionDialog(mainView, loginPanel,
				Constansts.LOGIN_DIALOG_TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, loginBtns, loginBtns[0]);
		if (result == JOptionPane.OK_OPTION) {

			String message = Controller.validateUser(new StoreKeeper(
					userNameTxtField.getText().trim(), passwordField.getText()
							.trim()));
			if (message.equals(Constansts.LOGIN_SUCCESS_MESSAGE)) {
				JOptionPane.showMessageDialog(null, message, "Message",
						JOptionPane.ERROR_MESSAGE);
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

	public JComponent getComponent() {
		return mainPanel;
	}

}

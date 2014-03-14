package com.iss.storeApplication.view;
import com.iss.storeApplication.common.Constansts;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPopupView {
	
	JButton login,cancel;
	JTextField uname;
	JPasswordField pass;
	JLabel u,p;
	static String[] ConnectOptionNames = { "Login", "Cancel" };
	

	public static boolean showLoginDialog(MainView mainView)
	{
		JPanel loginPanel=new JPanel();
		JTextField uName = new JTextField();
        JPasswordField pwd = new JPasswordField();   
        loginPanel.setLayout(new GridLayout(2, 3));
        loginPanel.setBackground( new Color(255, 0, 0, 20) );        
        loginPanel.setOpaque(false); 
        loginPanel.add(new JLabel("User Name:"));
        loginPanel.add(uName);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(pwd);  
		int result =  JOptionPane.showOptionDialog(mainView, loginPanel, 
			   Constansts.LOGIN_DIALOG_TITLE,
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.INFORMATION_MESSAGE,
                null, ConnectOptionNames, 
                ConnectOptionNames[0]);
		if (result == JOptionPane.OK_OPTION) {	
			
			return true;		
			
              
        } else {
        	mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            return false;        
	}

	}
	
	

}

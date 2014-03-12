package com.iss.storeApplication.view;

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;


public class MainView extends JFrame{	
	
	public void display() {
        JTextField uName = new JTextField();
        JPasswordField pwd = new JPasswordField();        
        JPanel panel = new JPanel(new GridLayout(0, 1)); 
        panel.setBackground( new Color(255, 0, 0, 20) );        
        panel.setOpaque(false); 
        panel.add(new JLabel("User Name:"));
        panel.add(uName);
        panel.add(new JLabel("Password:"));
        panel.add(pwd);
        int result = JOptionPane.showConfirmDialog(null, panel, "Login",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {        	
        	LoginPopupView lview = new LoginPopupView();
        	lview.a();           
        } else {
            System.out.println("Cancelled");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(false);
        }
    }

}

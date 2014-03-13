package com.iss.storeApplication.view;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPopupView  extends JFrame{
	
	JButton login,cancel;
	JTextField uname;
	JPasswordField pass;
	JLabel u,p;
	static String[] ConnectOptionNames = { "Login", "Cancel" };
	static String   ConnectTitle = "Login Screen";
	static JPanel panel = new JPanel(new GridLayout(0, 1)); 
	
	public LoginPopupView ()
	{
		
		JTextField uName = new JTextField();
        JPasswordField pwd = new JPasswordField();   
        
        panel.setBackground( new Color(255, 0, 0, 20) );        
        panel.setOpaque(false); 
        panel.add(new JLabel("User Name:"));
        panel.add(uName);
        panel.add(new JLabel("Password:"));
        panel.add(pwd);   
		
	}
	
	public boolean Login()
	{
		int result =  JOptionPane.showOptionDialog(null, panel, 
                ConnectTitle,
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.INFORMATION_MESSAGE,
                null, ConnectOptionNames, 
                ConnectOptionNames[0]);
		if (result == JOptionPane.OK_OPTION) {        	
			return true;
              
        } else {
            System.out.println("Cancelled");
            return false;
        
	}

	}

}

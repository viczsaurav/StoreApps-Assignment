package com.iss.storeApplication.view;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JPanel;

public class LogoutView extends JPanel {
	
	public LogoutView(){
		add(new Label("You have Logged out of the application.Login Again !!!")).setFont(new Font("Serif", Font.BOLD, 14));
	}

}

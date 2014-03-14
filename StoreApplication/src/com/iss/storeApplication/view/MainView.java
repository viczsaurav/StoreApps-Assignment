package com.iss.storeApplication.view;


import java.awt.FlowLayout;
import javax.swing.JFrame;

public class MainView extends JFrame{

public MainView() {
		
		initMainView();
	}


	private void initMainView() {
		
		setVisible(true);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);		
		setTitle("University Souvenir Store Application - SE224FT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void showLoginView() {
		
		LoginPopupView.showLoginDialog(this);
		
	}

}

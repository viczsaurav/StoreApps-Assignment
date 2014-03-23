package com.iss.storeApplication.view;


import java.awt.BorderLayout;
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
		addDefaultToolbar();
		
	}
	/**
	 * 
	 * @author sakthi
	 * 
	 */

	public void showLoginView() {
		
		LoginPopupView.showLoginDialog(this);
		
	}
	
	public void addDefaultToolbar(){
		ToolBarView toolBarView = new ToolBarView();
		add(toolBarView, BorderLayout.NORTH);
	}
}

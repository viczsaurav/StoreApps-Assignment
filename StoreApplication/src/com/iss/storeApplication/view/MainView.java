package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JFrame;

/**
 * 
 * @author milan
 *
 */
public class MainView extends JFrame {

	private static final Color gradientColor = new Color(107, 106, 104);
	private static final float gradientX = 1000;
	private static final float gradientY = 1000;
	
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

	public void addDefaultToolbar() {
		ToolBarView toolBarView = new ToolBarView();
		add(toolBarView, BorderLayout.NORTH);
	}

	
	
	
}

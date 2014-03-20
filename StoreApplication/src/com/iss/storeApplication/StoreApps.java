package com.iss.storeApplication;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.iss.storeApplication.view.MainView;
/**
 * 
 * @author sakthi
 * 
 */
public class StoreApps {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainView mainView = new MainView();
				mainView.showLoginView();
				
				}
		});

	}

}

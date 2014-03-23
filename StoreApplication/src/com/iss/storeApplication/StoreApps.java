package com.iss.storeApplication;


import javax.swing.SwingUtilities;

import com.iss.storeApplication.view.LoginPopupView;
import com.iss.storeApplication.view.MainView;
/**
 * 
 * @author Milan
 * 
 */
public class StoreApps {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainView mainView = new MainView();
				mainView.getContentPane().add(new LoginPopupView().getComponent());  
				mainView.showLoginView();
				}
		});

	}

}

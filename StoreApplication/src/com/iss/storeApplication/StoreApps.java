package com.iss.storeApplication;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import sun.tools.jar.Main;

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
				final MainView mainView = new MainView();
				//mainView.getContentPane().add(
				//new LoginPopupView().getComponent());
				mainView.showLoginView();
				
				/**
				 *
				 * @author Sakthi
				 *
				 */
				mainView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				mainView.addWindowListener( new WindowAdapter()
				{
				    public void windowClosing(WindowEvent e)
				    {			        

				        int result = JOptionPane.showConfirmDialog(
				            mainView,
				            "Are you sure you want to exit the application?",
				            "Exit Application",
				            JOptionPane.YES_NO_OPTION);

				        if (result == JOptionPane.YES_OPTION)
				            mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				       
				        		       
				    }
				});


			}
		});

	}

}

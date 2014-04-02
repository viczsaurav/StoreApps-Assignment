package com.iss.storeApplication;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.view.MainView;
import com.iss.storeApplication.view.ToolBarView;

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
				/**
				 * 
				 * @author Sakthi
				 * 
				 */
				mainView.getContentPane().setLayout(
						new BoxLayout(mainView.getContentPane(),
								BoxLayout.Y_AXIS));
				mainView.add(mainView.getContentPanel());
				mainView.showLoginView();
				mainView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				mainView.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {

						int result = JOptionPane
								.showConfirmDialog(
										mainView,
										"Are you sure you want to exit the application?",
										"Exit Application",
										JOptionPane.YES_NO_OPTION);

						if (result == JOptionPane.YES_OPTION)
							mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						else {
							if (ToolBarView.getToolbarName().equals(Utility.getPropertyValue(Constants.logout))) {
								mainView.showLoginView();
							}

						}

					}
				});

			}
		});

	}

}

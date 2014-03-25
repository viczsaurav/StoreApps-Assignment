package com.iss.storeApplication.common;

import javax.swing.JPanel;

public class SwingUtility {

	public static void refreshJpanel(JPanel panel)
	{
		panel.revalidate();
		panel.repaint();
	}
}

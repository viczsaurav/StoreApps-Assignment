package com.iss.storeApplication.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Product;

public class BillingView extends JPanel {

	
	private MainView mainView;
	private JLabel barCodeLbl = new JLabel(
			Utility.getPropertyValue(Constants.barcode));
	private JFormattedTextField barCodeTextField = new JFormattedTextField(
			Utility.getProductBarcodeFormat());
	private JButton findProductBtn = new JButton(
			Utility.getPropertyValue(Constants.find));
	private JPanel findProductPanel = new JPanel();

	public BillingView(MainView mainView) {
		this.mainView=mainView;
		
		barCodeTextField.setColumns(4);
		
		findProductPanel.setLayout(new FlowLayout());
		findProductPanel.add(barCodeLbl);
		findProductPanel.add(barCodeTextField);
		
		findProductPanel.add(findProductBtn);

		findProductBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				findProduct(barCodeTextField.getText());

			}
		});

		add(findProductPanel);
	}

	protected void findProduct(String barcode) {
		if (StringUtility.isEmpty(barcode)) {
			JOptionPane.showMessageDialog(mainView, Utility.getPropertyValue(Constants.barcodeNotNull));
			return;
		}
		Product p=Controller.getProduct(new Long(barcode));
		if(p==null)
		{
			JOptionPane.showMessageDialog(mainView, Utility.getPropertyValue(Constants.productNotFound));
			return;
		}
		System.out.println(p.getCommaSeperatedValue());
	}

}

package com.iss.storeApplication.view;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.enums.DiscountApplicable;
import com.iss.storeApplication.enums.DiscountType;

/**
 * 
 * @author milan
 *
 */
public class DiscountView extends JPanel {

	private Button addBtn = new Button(
			Utility.getPropertyValue(Constants.ADDDISCOUNT_BTN));
	private MainView mainView;

	public DiscountView(MainView mainView) {
		this.mainView = mainView;
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				addBtnClicked(event);

			}
		});
		add(addBtn);
	}

	protected void addBtnClicked(ActionEvent event) {

		addDiscountPopup();
	}

	private void addDiscountPopup() {

		final JPanel addDiscountPanel = new JPanel();
		addDiscountPanel.setMinimumSize(new Dimension(600, 600));
		addDiscountPanel.setPreferredSize(new Dimension(300, 300));
		addDiscountPanel.setLayout(new GridLayout(7, 4));
		JComboBox<DiscountType> discountTypeCmbBox = new JComboBox<DiscountType>();
		discountTypeCmbBox.setModel(new DefaultComboBoxModel<>(DiscountType
				.values()));

		JLabel discountTypeLabel = new JLabel(
				Utility.getPropertyValue(Constants.discountType));
		final JLabel discountCodeLabel = new JLabel(
				Utility.getPropertyValue(Constants.DISCOUNT_CODE));
		final JLabel decriptionLabel = new JLabel(
				Utility.getPropertyValue(Constants.Description));
		final JLabel discountApplicableLabel = new JLabel(
				Utility.getPropertyValue(Constants.discountApplicable));

		final JTextField discountCodeField = new JTextField();
		final JTextField descriptionField = new JTextField();
		final JComboBox<DiscountApplicable> discountApplicableCmbBox = new JComboBox<DiscountApplicable>();
		discountApplicableCmbBox.setModel(new DefaultComboBoxModel<>(
				DiscountApplicable.values()));

		discountTypeCmbBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(((JComboBox<DiscountType>) e.getSource())
							.getSelectedItem());
					Object selectedItem = ((JComboBox<DiscountType>) e
							.getSource()).getSelectedItem();
					if (DiscountType.PERMANENT_DISCOUNT.equals(selectedItem)) {

						addDiscountPanel.add(discountCodeLabel);
						addDiscountPanel.add(discountCodeField);

						addDiscountPanel.add(decriptionLabel);
						addDiscountPanel.add(descriptionField);

						addDiscountPanel.add(discountApplicableLabel);
						addDiscountPanel.add(discountApplicableCmbBox);

						SwingUtility.refreshJpanel(addDiscountPanel);
					} else {

					}
				}

			}
		});

		addDiscountPanel.add(discountTypeLabel);
		addDiscountPanel.add(discountTypeCmbBox);

		/*
		 * Object[] message = { Constants.CATEGORYID_LABEL, discountTypeCmbBox,
		 * Constants.CATEGORYNAME_LABEL, namefield };
		 */

		int result = JOptionPane.showConfirmDialog(mainView, addDiscountPanel,
				Utility.getPropertyValue(Constants.ADDDISCOUNT_BTN),
				JOptionPane.OK_CANCEL_OPTION);

		/*
		 * int option = JOptionPane.showInputDialog(null, addDiscountPanel,
		 * Constants.ADDCATEGORY_BTN, JOptionPane.OK_CANCEL_OPTION);
		 */

		/*
		 * if (option == JOptionPane.OK_OPTION) { String categoryCode =
		 * codeField.getText(); String categoryName = namefield.getText();
		 * System.out.println("categoryCode:" + categoryCode);
		 * System.out.println("categoryName:" + categoryName);
		 * 
		 * }
		 */

	}
}

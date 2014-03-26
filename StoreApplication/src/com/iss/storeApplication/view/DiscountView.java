package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.component.DatePicker;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.PermanentDiscount;
import com.iss.storeApplication.domain.SeasonalDiscount;
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
	private final JPanel addDiscountPanel = new JPanel();
	private JComboBox<DiscountType> discountTypeCmbBox = new JComboBox<DiscountType>();
	private final JLabel discountCodeLabel = new JLabel(
			Utility.getPropertyValue(Constants.DISCOUNT_CODE));
	private final JLabel decriptionLabel = new JLabel(
			Utility.getPropertyValue(Constants.Description));
	private final JLabel discountApplicableLabel = new JLabel(
			Utility.getPropertyValue(Constants.discountApplicable));
	private final JLabel discountLabel = new JLabel(
			Utility.getPropertyValue(Constants.discount));

	private final JLabel durationLabel = new JLabel(
			Utility.getPropertyValue(Constants.duration));

	private final JTextField discountCodeField = new JTextField(10);
	private final JTextField descriptionField = new JTextField(10);
	private final JComboBox<DiscountApplicable> discountApplicableCmbBox = new JComboBox<DiscountApplicable>();
	private JLabel discountTypeLabel = new JLabel(
			Utility.getPropertyValue(Constants.discountType));
	private final JFormattedTextField discountField = new JFormattedTextField(
			Utility.getPercentageNumberFormat());

	private final DatePicker startDatePicker = new DatePicker(
			Utility.getPropertyValue(Constants.startDate));
	private final JFormattedTextField durationField = new JFormattedTextField(
			Utility.getDurationNumberFormat());

	// Table
	private JTable discountTable = new JTable();
	private DiscountTableModel discountModel = new DiscountTableModel();

	private JButton deleteDiscountBtn = new JButton("Delete");

	public DiscountView(MainView mainView) {
		this.mainView = mainView;
		addBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				addBtnClicked(event);

			}
		});
		// add(addBtn);
		initAddDiscountDialog();
		initDiscountTable();
		refreshDiscountTable();
	}

	private void initDiscountTable() {
		discountTable.setModel(discountModel);
		add(new JScrollPane(discountTable), BorderLayout.CENTER);
		deleteDiscountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				deleteDiscount();
			}
		});
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new FlowLayout());
		panelButton.add(addBtn);
		panelButton.add(deleteDiscountBtn);

		add(panelButton, BorderLayout.PAGE_END);

	}

	private void addDiscount(Discount discount) {

		discountModel.addDiscount(discount);
		discountModel.fireTableDataChanged();

	}

	private void deleteDiscount() {
		int rowIndex = discountTable.getSelectedRow();
		if (rowIndex >= 0) {
			discountModel.removeDiscount(rowIndex);
			discountModel.fireTableDataChanged();

		}
	}

	protected void addBtnClicked(ActionEvent event) {

		showDiscountDialog();
	}

	private void initAddDiscountDialog() {
		addDiscountPanel.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();

		discountTypeCmbBox.setModel(new DefaultComboBoxModel<>(DiscountType
				.values()));

		discountApplicableCmbBox.setModel(new DefaultComboBoxModel<>(
				DiscountApplicable.values()));

		discountField.setColumns(10);
		discountField.setText("0");
		durationField.setColumns(10);
		durationField.setText("1");
		
		
		
		discountField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (StringUtility.isEmpty(discountField.getText())) {
					discountField.setText("0");
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		durationField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (StringUtility.isEmpty(durationField.getText())) {
					durationField.setText("1");
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		discountTypeCmbBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(((JComboBox<DiscountType>) e.getSource())
							.getSelectedItem());
					Object selectedItem = ((JComboBox<DiscountType>) e
							.getSource()).getSelectedItem();
					if (DiscountType.SEASONAL_DISCOUNT.equals(selectedItem)) {

						c.gridx = 0;
						c.gridy = 1;
						c.gridwidth = 2;
						addDiscountPanel.add(startDatePicker, c);
						c.gridwidth = 1;
						c.gridx = 0;
						c.gridy = 2;
						addDiscountPanel.add(durationLabel, c);
						c.gridx = 1;
						c.gridy = 2;
						addDiscountPanel.add(durationField, c);
					} else {
						addDiscountPanel.remove(startDatePicker);
						addDiscountPanel.remove(durationLabel);
						addDiscountPanel.remove(durationField);
					}

					SwingUtility.refreshJpanel(addDiscountPanel);
				}

			}
		});

		c.gridx = 0;
		c.gridy = 0;
		// c.fill = GridBagConstraints.BOTH;
		// c.insets = new Insets(5,5,5,5); // padding
		// c.anchor = GridBagConstraints.NORTHWEST;

		addDiscountPanel.add(discountTypeLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		addDiscountPanel.add(discountTypeCmbBox, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		addDiscountPanel.add(startDatePicker, c);
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 2;
		addDiscountPanel.add(durationLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		addDiscountPanel.add(durationField, c);
		c.gridx = 0;
		c.gridy = 3;
		addDiscountPanel.add(discountCodeLabel, c);
		c.gridx = 1;
		c.gridy = 3;
		addDiscountPanel.add(discountCodeField, c);

		c.gridx = 0;
		c.gridy = 4;
		addDiscountPanel.add(decriptionLabel, c);
		c.gridx = 1;
		c.gridy = 4;
		addDiscountPanel.add(descriptionField, c);
		c.gridx = 0;
		c.gridy = 5;
		addDiscountPanel.add(discountLabel, c);
		c.gridx = 1;
		c.gridy = 5;
		addDiscountPanel.add(discountField, c);
		c.gridx = 0;
		c.gridy = 6;
		addDiscountPanel.add(discountApplicableLabel, c);
		c.gridx = 1;
		c.gridy = 6;
		addDiscountPanel.add(discountApplicableCmbBox, c);
	}

	private void showDiscountDialog() {

		/*
		 * Object[] message = { Constants.CATEGORYID_LABEL, discountTypeCmbBox,
		 * Constants.CATEGORYNAME_LABEL, namefield };
		 */

		int result = JOptionPane.showConfirmDialog(mainView, addDiscountPanel,
				Utility.getPropertyValue(Constants.ADDDISCOUNT_BTN),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Object selectedItem = discountTypeCmbBox.getSelectedItem();

			// Seasonal Discount
			String message = "";
			if (DiscountType.SEASONAL_DISCOUNT.equals(selectedItem)) {
				SeasonalDiscount sd = new SeasonalDiscount();
				sd.setDescription(descriptionField.getText());
				sd.setDiscount(new Double(discountField.getText()));
				sd.setDiscountCode(discountCodeField.getText());
				sd.setDuration(new Integer(durationField.getText()));
				sd.setMemberApplicable((((DiscountApplicable) discountApplicableCmbBox
						.getSelectedItem()).toString()));
				sd.setStartDate(startDatePicker.getPickedDate());
				message = Controller.validateAndSaveDiscount(sd);
			} else {
				PermanentDiscount pd = new PermanentDiscount();
				pd.setDescription(descriptionField.getText());
				pd.setDiscount(new Double(discountField.getText()));
				pd.setDiscountCode(discountCodeField.getText());
				pd.setMemberApplicable((((DiscountApplicable) discountApplicableCmbBox
						.getSelectedItem()).toString()));
				message = Controller.validateAndSaveDiscount(pd);
			}

			if (message.equals(Constants.SUCCESS)) {
				JOptionPane.showMessageDialog(null, message);
				refreshDiscountTable();
			} else {
				JOptionPane.showMessageDialog(null, message, "Message",
						JOptionPane.ERROR_MESSAGE);
				showDiscountDialog();
			}
		}

	}
	
	
	
	public void refreshDiscountTable()
	{
		discountModel.clear();
		List<Discount> discounts=Controller.getDiscounts();
		for(Discount d:discounts)
		{
			addDiscount(d);
		}
	}
}

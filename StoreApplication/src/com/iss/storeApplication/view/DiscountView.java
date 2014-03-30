package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
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
import javax.swing.ListSelectionModel;

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

	private MainView mainView;

	// Add / Edit Discount Panel
	private final JPanel addDiscountPanel = new JPanel();
	private JComboBox<DiscountType> discountTypeCmbBox = new JComboBox<DiscountType>();
	private final JLabel discountCodeLabel = new JLabel(
			Utility.getPropertyValue(Constants.discountCode));
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

	// add Edit Delete
	private JButton deleteDiscountBtn = new JButton("Delete Discount");
	private JButton addDiscountBtn = new JButton(
			Utility.getPropertyValue(Constants.addDiscountBtn));
	private JButton editDiscountBtn = new JButton(
			Utility.getPropertyValue(Constants.editDiscount));

	public DiscountView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;
		addDiscountBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				addBtnClicked(event);

			}
		});
		editDiscountBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				editBtnClicked(event);

			}
		});
		
		//init add / edit discount panel
		initAddDiscountDialog();
		
		//init discount table
		initDiscountTable();
		
		//populate discounts in jtable
		refreshDiscountTable();
	}

	/**
	 * Edit Button Clicked. Edit Popup will show.
	 * 
	 * @param event
	 */
	protected void editBtnClicked(ActionEvent event) {
		int row = discountTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.selectRow));
			return;
		}

		Discount d = discountModel.getListDiscounts().get(row);
		showEditDiscountDialog(d);
	}

	/*
	 * Initialize Discount Jtable
	 */
	private void initDiscountTable() {

		discountTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel panelButton = new JPanel();
		panelButton.add(addDiscountBtn, BorderLayout.NORTH);
		panelButton.add(editDiscountBtn, BorderLayout.NORTH);
		panelButton.add(deleteDiscountBtn, BorderLayout.NORTH);
		add(panelButton, BorderLayout.NORTH);

		discountTable.setModel(discountModel);
		add(new JScrollPane(discountTable), BorderLayout.CENTER);
		deleteDiscountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				deleteDiscount();
			}
		});

	}

	/**
	 * Add Discount Object To JTable
	 * 
	 * @param discount
	 */
	private void addDiscount(Discount discount) {

		discountModel.addDiscount(discount);
		discountModel.fireTableDataChanged();

	}

	/**
	 * Delete Discount from file and Jtable
	 */
	private void deleteDiscount() {

		int rowIndex = discountTable.getSelectedRow();
		if(rowIndex==-1)
		{
			JOptionPane.showMessageDialog(mainView, Utility.getPropertyValue(Constants.selectRow));
			return;
		}
		
		if (rowIndex >= 0) {
			discountModel.removeDiscount(rowIndex);

		}
		if (Controller.saveAll(discountModel.getListDiscounts())) {
			discountModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.failure));
		}
	}

	/**
	 * Save Edited discount to file and reflect edited changes to Jtable
	 * 
	 * @param d
	 */
	private void editDiscount(Discount d) {

		int rowIndex = discountTable.getSelectedRow();

		discountModel.getListDiscounts().set(rowIndex, d);

		if (Controller.saveAll(discountModel.getListDiscounts())) {
			discountModel.fireTableDataChanged();
			
		} else {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.failure));
		}
	}

	/**
	 * Add Discount Button Clicked. Add Discount Popup will appear.
	 * 
	 * @param event
	 */
	protected void addBtnClicked(ActionEvent event) {

		showAddDiscountDialog();
	}

	/**
	 * Initialize Add/Edit Discount Panel
	 */
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

		discountCodeField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (Controller.isDiscountCodeAlreadyExist(discountCodeField
						.getText())) {
					JOptionPane.showMessageDialog(mainView,
							Utility.getPropertyValue(Constants.dcAlreadyExist));
					discountCodeField.setText("");
				}

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

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

	/**
	 * set discount object property to Add / Edit Discount Panel
	 * 
	 * @param d
	 */
	private void setDiscountToDiscountDialogView(Discount d) {
		if (d != null) {
			if (d instanceof SeasonalDiscount) {
				SeasonalDiscount sd = (SeasonalDiscount) d;
				discountTypeCmbBox
						.setSelectedItem(DiscountType.SEASONAL_DISCOUNT);
				startDatePicker.setDate(sd.getStartDate());
				durationField.setText(sd.getDuration().toString());
			} else {

				discountTypeCmbBox
						.setSelectedItem(DiscountType.PERMANENT_DISCOUNT);
			}
			discountCodeField.setText(d.getDiscountCode());
			discountField.setText(d.getDiscount().toString());
			descriptionField.setText(d.getDescription());
			discountApplicableCmbBox.setSelectedItem(d.getMemberApplicable());

		}
		else // reset to default value
		{
			durationField.setText("1");
			discountCodeField.setText("");
			discountField.setText("1");
			descriptionField.setText("");
			startDatePicker.setDate(new Date());
		}
	}

	/**
	 * Show Add Discount Popup
	 * 
	 * @param d
	 */
	private void showAddDiscountDialog() {
		
		//reset discount panel to default value
		setDiscountToDiscountDialogView(null);
		
		/*
		 * Object[] message = { Constants.CATEGORYID_LABEL, discountTypeCmbBox,
		 * Constants.CATEGORYNAME_LABEL, namefield };
		 */

		int result = JOptionPane.showConfirmDialog(mainView, addDiscountPanel,
				Utility.getPropertyValue(Constants.addDiscountBtn),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Object selectedItem = discountTypeCmbBox.getSelectedItem();

			// Seasonal Discount
			String message = "";
			if (DiscountType.SEASONAL_DISCOUNT.equals(selectedItem)) {

				SeasonalDiscount sd = createSeasonalDiscountFromView();
				message = Controller.validateAndSaveDiscount(sd);
			} else {
				PermanentDiscount pd = createPermanentDiscountFromView();
				message = Controller.validateAndSaveDiscount(pd);
			}

			if (message.equals(Constants.SUCCESS)) {
				JOptionPane.showMessageDialog(null, message);
				refreshDiscountTable();
			} else {
				JOptionPane.showMessageDialog(null, message, "Message",
						JOptionPane.ERROR_MESSAGE);
				showAddDiscountDialog();
			}
		}

	}

	/**
	 * It shows edit Discount Popup.
	 * 
	 * @param d
	 */
	private void showEditDiscountDialog(Discount d) {

		/*
		 * Object[] message = { Constants.CATEGORYID_LABEL, discountTypeCmbBox,
		 * Constants.CATEGORYNAME_LABEL, namefield };
		 */

		setDiscountToDiscountDialogView(d);

		int result = JOptionPane.showConfirmDialog(mainView, addDiscountPanel,
				Utility.getPropertyValue(Constants.editDiscount),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			Object selectedItem = discountTypeCmbBox.getSelectedItem();
			Discount discount = null;
			if (DiscountType.SEASONAL_DISCOUNT.equals(selectedItem)) {

				discount = createSeasonalDiscountFromView();

			} else {
				discount = createPermanentDiscountFromView();

			}
			String msg = Controller.validateDiscount(discount);
			if (msg.equals(Constants.SUCCESS))
				editDiscount(discount);
			else {
				JOptionPane.showMessageDialog(mainView, msg, "Message",
						JOptionPane.ERROR_MESSAGE);

				showEditDiscountDialog(discount);
			}

		}

	}

	/**
	 * create Permanent Discount Object from Add / Edit Discount Panel
	 * 
	 * @return
	 */
	private PermanentDiscount createPermanentDiscountFromView() {
		PermanentDiscount pd = new PermanentDiscount();
		pd.setDescription(descriptionField.getText());
		pd.setDiscount(new Double(discountField.getText()));
		pd.setDiscountCode(discountCodeField.getText());
		pd.setMemberApplicable((((DiscountApplicable) discountApplicableCmbBox
				.getSelectedItem())));
		return pd;
	}

	/**
	 * create Seasonal Discount Object from Add / Edit Discount Panel
	 * 
	 * @return
	 */
	private SeasonalDiscount createSeasonalDiscountFromView() {
		SeasonalDiscount sd = new SeasonalDiscount();
		sd.setDescription(descriptionField.getText());
		sd.setDiscount(new Double(discountField.getText()));
		sd.setDiscountCode(discountCodeField.getText());
		sd.setDuration(new Integer(durationField.getText()));
		sd.setMemberApplicable((((DiscountApplicable) discountApplicableCmbBox
				.getSelectedItem())));
		sd.setStartDate(startDatePicker.getPickedDate());
		return sd;
	}

	/**
	 * refreshes Jtable
	 */
	public void refreshDiscountTable() {
		discountModel.clear();
		List<Discount> discounts = Controller.getDiscounts();
		for (Discount d : discounts) {
			addDiscount(d);
		}
	}
}

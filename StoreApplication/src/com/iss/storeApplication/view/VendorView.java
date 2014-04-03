package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Vendor;

/**
 * 
 * @author milan
 *
 */
public class VendorView extends JPanel {

	private MainView mainView;

	private JPanel topPanel = new JPanel();
	private JComboBox<Category> categoryCmbBox = new JComboBox<Category>();
	private CategoryComboboxModel categoryComboboxModel = new CategoryComboboxModel(
			categoryCmbBox);
	private JButton addVendorBtn = new JButton(
			Utility.getPropertyValue(Constants.addVendor));
	private JButton deleteVendorBtn = new JButton(
			Utility.getPropertyValue(Constants.deleteVendor));

	private JPanel addVendorPanel = new JPanel(new GridBagLayout());
	private JLabel vendorNameLbl = new JLabel(
			Utility.getPropertyValue(Constants.vendorName));
	private JTextField vendorNameField = new JTextField(10);
	private JLabel vendorDescLbl = new JLabel(
			Utility.getPropertyValue(Constants.vendorDesc));
	private JTextField vendorDescField = new JTextField(10);

	private JLabel categoryLbl = new JLabel(
			Utility.getPropertyValue(Constants.category));
	private JPanel categoryPanel = new JPanel();
	private JPanel categoryComboboxPanel = new JPanel();
	private JButton categoryAddBtn = new JButton("+");

	private JPanel centerPanel = new JPanel();
	private JTable vendorTable = new JTable();
	private VendorTableModel vendorTableModel = new VendorTableModel();

	public VendorView(MainView mainView) {
		super(new BorderLayout());

		initTopPanel();

		initAddVendorPanel();

		initCenterPanel();
	}

	private void initCenterPanel() {
		vendorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		vendorTable.setModel(vendorTableModel);
		vendorTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
		vendorTable.setPreferredSize(new Dimension(600, 400));
		vendorTable.setSize(new Dimension(600, 400));
		JScrollPane sp = new JScrollPane(vendorTable);
		sp.setPreferredSize(new Dimension(600, 400));
		sp.setMaximumSize(new Dimension(600, 400));
		centerPanel.add(sp);
		add(centerPanel, BorderLayout.CENTER);

	}

	private void showAddVendorPanel() {
		int result = JOptionPane.showConfirmDialog(mainView, addVendorPanel,
				Utility.getPropertyValue(Constants.addVendor),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			// validate vendor
			Vendor v = new Vendor();
			v.setName(vendorNameField.getText());
			v.setDescription(vendorDescField.getText());
			List<Category> categories = new ArrayList<Category>();

			for (Component c : categoryPanel.getComponents()) {
				String categoryCode = ((JLabel) ((JPanel) c).getComponent(0))
						.getText();

				categories.add(categoryComboboxModel
						.getCategoryCodeCategoryMap().get(categoryCode));

			}

			v.setCategories(categories);

			String msg = Controller.validateVendor(v);
			if (msg.equals(Constants.SUCCESS)) {
				if (Controller.saveVendor(v)) {
					JOptionPane.showMessageDialog(mainView, Utility
							.getPropertyValue(Constants.msgVendorAddedSuccess));

				} else {
					JOptionPane.showMessageDialog(mainView,
							Utility.getPropertyValue(Constants.failure));
				}
				refreshTable();
			} else {
				JOptionPane.showMessageDialog(mainView,
						Utility.getPropertyValue(msg));
				showAddVendorPanel();
			}
		}
		
		resetVendorAddPanel();
	}

	private void initTopPanel() {
		topPanel.add(addVendorBtn);
		topPanel.add(deleteVendorBtn);
		addVendorBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showAddVendorPanel();

			}
		});
		
		deleteVendorBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				deleteVendor();
				
			}
		});
		
		add(topPanel, BorderLayout.NORTH);
		refreshTable();
		

	}

	protected void deleteVendor() {
		int rowIndex = vendorTable.getSelectedRow();
		if(rowIndex==-1)
		{
			JOptionPane.showMessageDialog(mainView, Utility.getPropertyValue(Constants.selectRow));
			return;
		}
		
		
		if (Controller.deleteVendor(vendorTableModel.getVendors().get(rowIndex))) {
			vendorTableModel.fireTableDataChanged();
			if (rowIndex >= 0) {
				vendorTableModel.removeVendor(rowIndex);

			}
		} else {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.failure));
		}
		
	}

	private void initAddVendorPanel() {

		final GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;

		
		categoryComboboxPanel.add(categoryCmbBox);
		categoryComboboxPanel.add(categoryAddBtn);
	

		addVendorPanel.add(vendorNameLbl, c);
		c.gridx = 1;
		c.gridy = 0;
		addVendorPanel.add(vendorNameField, c);
		c.gridx = 0;
		c.gridy = 1;
		addVendorPanel.add(vendorDescLbl, c);
		c.gridx = 1;
		c.gridy = 1;
		addVendorPanel.add(vendorDescField, c);
		c.gridx = 0;
		c.gridy = 2;
		addVendorPanel.add(categoryLbl, c);
		c.gridx = 1;
		c.gridy = 2;
		addVendorPanel.add(categoryComboboxPanel, c);
		c.gridx = 1;
		c.gridy = 3;

		categoryPanel.setPreferredSize(new Dimension(200, 200));
		// categoryPanel.setMaximumSize(new Dimension(200,200));
		addVendorPanel.add(categoryPanel, c);
		// addVendorPanel.setMaximumSize(new Dimension(400,400));
		addVendorPanel.setPreferredSize(new Dimension(400, 400));
		categoryAddBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createAddCategoryPanel();

			}
		});
		
		refreshCategoryCombobox();

	}

	protected void createAddCategoryPanel() {
		JLabel l = new JLabel(categoryComboboxModel.getSelectedCategory()
				.getCategoryCode());
		JButton del = new JButton("X");
		final JPanel category = new JPanel();
		category.add(l);
		category.add(del);

		if (categoryPanel.getComponents().length >= 10) {
			JOptionPane.showMessageDialog(mainView,
					"Cannot add more than 10 category");
			return;
		}
		for (Component c : categoryPanel.getComponents()) {
			if (((JLabel) ((JPanel) c).getComponent(0)).getText().equals(
					categoryComboboxModel.getSelectedCategory()
							.getCategoryCode())) {
				JOptionPane.showMessageDialog(mainView,
						"Category Already Added");
				return;
			}
		}

		categoryPanel.add(category);
		SwingUtility.refreshJpanel(addVendorPanel);
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				categoryPanel.remove(category);
				SwingUtility.refreshJpanel(addVendorPanel);

			}
		});

	}

	public void refreshTable() {
		List<Vendor> vendors=Controller.retrieveAllVendor();
		vendorTableModel.setVendors(vendors);
		vendorTableModel.fireTableDataChanged();
	}
	
	public void refreshCategoryCombobox()
	{
		categoryComboboxModel.setCategories(Controller.getAllCategory());
		categoryCmbBox.setModel(categoryComboboxModel);
		categoryCmbBox.setSelectedIndex(0);
		
	}
	
	public void resetVendorAddPanel()
	{
		vendorDescField.setText("");
		vendorNameField.setText("");
		categoryPanel.removeAll();
	}

}

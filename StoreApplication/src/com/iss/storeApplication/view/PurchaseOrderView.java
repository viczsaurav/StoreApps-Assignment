package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.PurchaseOrder;
import com.iss.storeApplication.domain.Vendor;

/**
 * 
 * @author milan
 *
 */
public class PurchaseOrderView extends JPanel {

	private MainView mainView;

	private JPanel topPanel = new JPanel();
	private JComboBox<Category> categoryCmbBox = new JComboBox<Category>();
	private JButton generatePurchaseOrderBtn = new JButton(
			Utility.getPropertyValue(Constants.generatePurchaseOrder));

	private JTable productTabl = new JTable();
	private PurchaseOrderProductTableModel purchaseOrderProductTableModel = new PurchaseOrderProductTableModel();
	private PurchaseOrderModel purchaseOrderModel = new PurchaseOrderModel();

	private JPanel centerPanel = new JPanel();
	private CategoryComboboxModel categoryComboboxModel = new CategoryComboboxModel(
			categoryCmbBox);

	private JTable purchaseOrderTable = new JTable();

	public PurchaseOrderView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;

		initTopPanel();

		initCenterPanel();
	}

	/**
	 * Init Center Panel
	 */
	private void initCenterPanel() {

		productTabl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		productTabl.setModel(purchaseOrderProductTableModel);
		productTabl.setPreferredScrollableViewportSize(new Dimension(600, 400));
		productTabl.setPreferredSize(new Dimension(600, 400));
		productTabl.setSize(new Dimension(600, 400));
		JScrollPane sp = new JScrollPane(productTabl);
		sp.setPreferredSize(new Dimension(600, 400));
		sp.setMaximumSize(new Dimension(600, 400));
		centerPanel.add(sp);
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * Init Top Panel
	 */
	private void initTopPanel() {
		topPanel.add(categoryCmbBox);
		topPanel.add(generatePurchaseOrderBtn);
		add(topPanel, BorderLayout.NORTH);

		// retrieve all category
		List<Category> categories = Controller.getAllCategory();
		categoryComboboxModel.setCategories(categories);
		categoryCmbBox.setModel(categoryComboboxModel);

		categoryCmbBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					refreshProductTable();
				}

			}
		});
		categoryCmbBox.setSelectedIndex(0);

		generatePurchaseOrderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				generatePurchaseOrder();

			}
		});

	}

	/**
	 * Generate Purchase Order and update available qty of product
	 */
	protected void generatePurchaseOrder() {

		// find vendor
		Category c = categoryComboboxModel.getSelectedCategory();
		Vendor v = Controller.getFirstVendor(c.getCategoryCode());
		if (v == null) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.msgNoVendorFound));
			return;
		}

		List<PurchaseOrder> purchaseOrders = purchaseOrderProductTableModel
				.getPurchaseOrders();

		for (PurchaseOrder po : purchaseOrders) {
			po.setVendor(v);
			po.setOrderDate(new Date());
			po.setOrderedQty(po.getProduct().getOrderQty());

			// increase all product quantity and save to file
			Product p = po.getProduct();
			p.setQtyAvailable(p.getQtyAvailable() + p.getOrderQty());
			if (!Controller.editProduct(p)) {
				JOptionPane.showMessageDialog(mainView,
						Utility.getPropertyValue(Constants.failure));
				return;
			}

		}
		purchaseOrderTable.setModel(purchaseOrderModel);
		purchaseOrderModel.setPurchaseOrders(purchaseOrders);
		JScrollPane sp = new JScrollPane(purchaseOrderTable);
		JOptionPane.showMessageDialog(mainView, sp,
				Utility.getPropertyValue(Constants.purchaseOrder),
				JOptionPane.OK_OPTION);

		resetTable();

	}

	/**
	 * Refresh Product Table
	 */
	protected void refreshProductTable() {
		// get list of product belong to category

		List<Product> products = Controller
				.getProductsBelowThreshold(categoryComboboxModel
						.getSelectedCategory());
		List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
		for (Product p : products) {
			PurchaseOrder po = new PurchaseOrder();
			po.setProduct(p);
			purchaseOrders.add(po);
		}
		purchaseOrderProductTableModel.setPurchaseOrders(purchaseOrders);
		purchaseOrderProductTableModel.fireTableDataChanged();
	}

	/**
	 * Reset TAble after  generating purchase order
	 */
	public void resetTable() {
		purchaseOrderModel.setPurchaseOrders(new ArrayList<PurchaseOrder>());
		
		purchaseOrderModel.fireTableDataChanged();
		refreshProductTable();

	}
}

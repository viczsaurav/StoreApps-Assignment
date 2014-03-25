package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.SwingUtility;

/**
 * 
 * @author Saurav
 * 
 */
public class ProductView extends JPanel {

	private JPanel productPanel;
	private JTable productTable;
	private DefaultTableModel productTableModel;
	private JComboBox<String> prodCategory;
	private JButton addNewProduct;
	
	private MainView mainView;

	// Constructor
	public ProductView(MainView mainView) {
		this.mainView = mainView;
		setBounds(100, 100, 580, 242);
		setLayout(new BorderLayout());
		initializeProduct();
	}

	private void initializeProduct() {
		productPanel = new JPanel();
		productTable = new JTable();
		addNewProduct = new JButton(Constants.ADDPRODUCT_BTN);
		
		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		scrollPane.setViewportView(productTable);
		
		// Adding Add New Product Button to  Panel
		productPanel.add(addNewProduct, BorderLayout.EAST);
		
		// define Model for Table
		productTableModel = new DefaultTableModel() {
					public Class<?> getColumnClass(int column) {
						switch (column) {
						case 0:
							return String.class;
						case 1:
							return String.class;

						default:
							return String.class;
						}
					}

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
				
		productTable.setModel(productTableModel);
		
		// Adding Columns
		productTableModel.addColumn(Constants.PRODUCTID_LABEL);
		productTableModel.addColumn(Constants.PRODUCTNAME_LABEL);
		productTableModel.addColumn(Constants.PRODUCT_DESC_LABEL);
		productTableModel.addColumn(Constants.PRODUCT_QUANTITY_LABEL);
		productTableModel.addColumn(Constants.PRODUCT_PRICE_LABEL);
		productTableModel.addColumn(Constants.PRODUCT_BARCODE_LABEL);
		productTableModel.addColumn(Constants.PRODUCT_REORDER_QUANT_LABEL);
		productTableModel.addColumn(Constants.PRODUCT_ORDER_QUANT_LABEL);
		
		// Initializing ComboBox
		final ComboBoxModel<String> jComboBoxModel = new DefaultComboBoxModel<>(
				new String[] { "Item One", "Item Two" });
		prodCategory = new JComboBox<>();
		prodCategory.setModel(jComboBoxModel);

		// define add button function
		addNewProduct.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				// Adding Item listener for ComboBox
				prodCategory.addItemListener(new ItemListener() {
					
					@Override
					public void itemStateChanged(ItemEvent e) {
						if ((e.getStateChange() == ItemEvent.SELECTED)) {
		                    String result = (String) prodCategory.getSelectedItem();
		                    // GET CATEGORY VALUE HERE
						}
					}
				});

				JTextField prodName 		= new JTextField();
				JTextField prodDesc 		= new JTextField();
				JTextField prodQuant 		= new JTextField();
				JTextField prodPrice 		= new JTextField();
				JTextField prodBarCode 		= new JTextField();
				JTextField prodReorderQuant = new JTextField();
				JTextField prodOrderQuant 	= new JTextField();

				Object[] productFields = { 
						Constants.PRODUCT_CATEGORY_LABEL, prodCategory, 
						Constants.PRODUCTNAME_LABEL, prodName,
						Constants.PRODUCT_DESC_LABEL, prodDesc,
						Constants.PRODUCT_QUANTITY_LABEL, prodQuant,
						Constants.PRODUCT_PRICE_LABEL, prodPrice,
						Constants.PRODUCT_BARCODE_LABEL, prodBarCode,
						Constants.PRODUCT_REORDER_QUANT_LABEL, prodReorderQuant,
						Constants.PRODUCT_ORDER_QUANT_LABEL, prodOrderQuant };

				int option = JOptionPane.showConfirmDialog(null, productFields,
						Constants.ADDPRODUCT_BTN, JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String productName = prodName.getText();
					String prodDescription = prodDesc.getText();
					System.out.println("categoryCode:" + productName);
					System.out.println("categoryName:" + prodDescription);
					// freshCategory(productTableModel);
					
					// Refreshing the Panel
					SwingUtility.refreshJpanel(productPanel);
				}

			}
		});
		addNewProduct.setBounds(224, 149, 131, 23);
		add(productPanel, BorderLayout.NORTH);
		add(scrollPane);
		
	}
}

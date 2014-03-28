package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ComboBoxModel;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.dao.CategoryDao;
import com.iss.storeApplication.dao.ProductDao;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Product;

/**
 * 
 * @author Saurav
 * 
 */
public class ProductView extends JPanel {

	private MainView mainView;
	private List<Category> category;
	private List<Product> allProduct;
	private String[] allCategoryName;
	private String selectedCategory;

	CategoryDao fetchCategory = new CategoryDao();

/**
 * 
 * GUI
 * 
 */
	// Add / Edit Product
	private final JPanel productPanel = new JPanel();
	private JComboBox<String> prodCategory = new JComboBox<>();
	private final JTextField prodName = new JTextField();
	private final JTextField prodDesc = new JTextField();
	private final JFormattedTextField prodQuant = new JFormattedTextField(
			Utility.getProductNumberFormat());
	private final JFormattedTextField prodPrice = new JFormattedTextField(
			Utility.getProductPriceFormat());
	private final JFormattedTextField prodBarCode = new JFormattedTextField(
			Utility.getProductBarcodeFormat());
	private final JFormattedTextField prodReorderQuant = new JFormattedTextField(
			Utility.getProductNumberFormat());
	private final JFormattedTextField prodOrderQuant = new JFormattedTextField(
			Utility.getProductNumberFormat());

	// Table
	private JTable productTable = new JTable();
	private DefaultTableModel productTableModel;

	// Buttons
	private JButton addNewProduct = new JButton(Constants.ADDPRODUCT_BTN);
	private JButton editProduct = new JButton("Edit Product");
	private JButton deleteProduct = new JButton("Delete Product");
 /**
 * 
 */

	
	// Constructor
	public ProductView(MainView mainView) {
		this.mainView = mainView;
		setBounds(100, 100, 580, 242);
		setLayout(new BorderLayout());
		initializeProduct();
	}

	private void initializeProduct() {

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		scrollPane.setViewportView(productTable);

		// Adding Buttons to Panel
		productPanel.add(addNewProduct, BorderLayout.CENTER);
		productPanel.add(editProduct, BorderLayout.CENTER);
		productPanel.add(deleteProduct, BorderLayout.CENTER);

		// define add button function
		addNewProduct.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				// CategoryDao

				// Adding Item listener for ComboBox
				prodCategory.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if ((e.getStateChange() == ItemEvent.SELECTED)) {
							String result = (String) prodCategory
									.getSelectedItem();
							selectedCategory = result.split("-")[0].trim();
							System.out.println(selectedCategory);
						}
					}
				});

				Object[] productFields = { Constants.PRODUCT_CATEGORY_LABEL,
						prodCategory, Constants.PRODUCTNAME_LABEL, prodName,
						Constants.PRODUCT_DESC_LABEL, prodDesc,
						Constants.PRODUCT_QUANTITY_LABEL, prodQuant,
						Constants.PRODUCT_PRICE_LABEL, prodPrice,
						Constants.PRODUCT_BARCODE_LABEL, prodBarCode,
						Constants.PRODUCT_REORDER_QUANT_LABEL,
						prodReorderQuant, Constants.PRODUCT_ORDER_QUANT_LABEL,
						prodOrderQuant };

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

		// define Edit button function
		editProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		// define Delete button function
		deleteProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});



		// Getting Category Name
		category = fetchCategory.retrieveAll();
		allCategoryName = new String[category.size()];
		for (int i = 0; i < category.size(); i++) {
			allCategoryName[i] = category.get(i).getCategoryCode() + " - "
					+ category.get(i).getCategoryName();
		}
		// Initializing ComboBox
		ComboBoxModel<String> jComboBoxModel = new DefaultComboBoxModel<>(
				allCategoryName);
		prodCategory.setModel(jComboBoxModel);

		addNewProduct.setBounds(224, 149, 131, 23);
		add(productPanel, BorderLayout.NORTH);
		add(scrollPane);
		// get and display category list
		refreshProduct(productTableModel);
	}
	
	private void refreshProduct(DefaultTableModel model) {
		// define Model for Table
		model = new DefaultTableModel() {
			public Class<?> getColumnClass(int column) {
				switch (column) {
				default:
					return String.class;
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Center Align Column values
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		productTable.setDefaultRenderer(String.class, centerRenderer);

		productTable.setModel(model);	
		
		// Adding Columns
		model.addColumn(Constants.PRODUCTID_LABEL);
		model.addColumn(Constants.PRODUCTNAME_LABEL);
		model.addColumn(Constants.PRODUCT_DESC_LABEL);
		model.addColumn(Constants.PRODUCT_QUANTITY_LABEL);
		model.addColumn(Constants.PRODUCT_PRICE_LABEL);
		model.addColumn(Constants.PRODUCT_BARCODE_LABEL);
		model.addColumn(Constants.PRODUCT_REORDER_QUANT_LABEL);
		model.addColumn(Constants.PRODUCT_ORDER_QUANT_LABEL);

		ProductDao product = new ProductDao();
		allProduct = product.retrieveAll();

		// Putting Values in the Product Table
		int i = 0;
		for (Product entry : allProduct) {
			model.addRow(new Object[0]);
			model.setValueAt(entry.getProductId(), i, 0);
			model.setValueAt(entry.getProductName(), i, 1);
			model.setValueAt(entry.getDescription(), i, 2);
			model.setValueAt(entry.getQtyAvailable(), i, 3);
			model.setValueAt(entry.getPrice(), i, 4);
			model.setValueAt(entry.getBarCode(), i, 5);
			model.setValueAt(entry.getReorderQty(), i, 6);
			model.setValueAt(entry.getOrderQty(), i, 7);
			i++;
		}

	}

}

package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainView mainView;
	private List<Category> category;
	private String[] allCategoryName;
	private String selectedCategory;

	private CategoryDao fetchCategory = new CategoryDao();
	private ProductDao fetchProduct = new ProductDao();

	/**
	 * 
	 * GUI
	 * 
	 */
	// Panel + Text Field
	private final JPanel productPanel = new JPanel();
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

	// Textfield Labels
	private final JLabel productCategoryLabel = new JLabel(
			Utility.getPropertyValue(Constants.productCategory));
	private final JLabel productNameLabel = new JLabel(
			Utility.getPropertyValue(Constants.productName));
	private final JLabel productDescriptionLabel = new JLabel(
			Utility.getPropertyValue(Constants.productDescription));
	private final JLabel productQtyLabel = new JLabel(
			Utility.getPropertyValue(Constants.productQty));
	private final JLabel productPriceLabel = new JLabel(
			Utility.getPropertyValue(Constants.productPrice));
	private final JLabel barcodeLabel = new JLabel(
			Utility.getPropertyValue(Constants.barcode));
	private final JLabel productReorderThresholdLabel = new JLabel(
			Utility.getPropertyValue(Constants.productReorderThreshold));
	private final JLabel productOrderQtyLabel = new JLabel(
			Utility.getPropertyValue(Constants.productOrderQty));

	// Table
	private JTable productTable = new JTable();
	private ProductTableModel productTableModel = new ProductTableModel();

	// Buttons
	private JButton addNewProduct = new JButton(
			Utility.getPropertyValue(Constants.addProductBtn));
	private JButton editProduct = new JButton(
			Utility.getPropertyValue(Constants.editProductBtn));
	private JButton deleteProduct = new JButton(
			Utility.getPropertyValue(Constants.deleteProductBtn));

	// ComboBox
	private JComboBox<String> productCategoryCmbBox = new JComboBox<String>();

	JPanel northPanel = new JPanel();

	// Constructor
	public ProductView(MainView mainView) {
		setLayout(new BorderLayout());
		this.mainView = mainView;

		// Add New Product
		addNewProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addBtnClicked(e);

			}
		});

		// Edit New Product
		editProduct.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editBtnClicked(e);

			}
		});

		// init add / edit Product panel
		initAddProductDialog();

		// init discount table
		initProductTable();

		// populate discounts in jtable
		refreshProductTable();
	}

	/**
	 * Add Discount Button Clicked. Add Product Popup will appear.
	 * 
	 * @param event
	 */
	protected void addBtnClicked(ActionEvent event) {

		showAddProductDialog();
	}

	/**
	 * Edit Button Clicked. Edit Popup Popup will show.
	 * 
	 * @param event
	 */
	protected void editBtnClicked(ActionEvent event) {
		int row = productTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.selectRow));
			return;
		}

		Product p = productTableModel.getListProducts().get(row);
		showEditProductDialog(p);
	}

	/*
	 * Initialize Discount Jtable
	 */
	private void initProductTable() {

		productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		northPanel.add(addNewProduct);
		northPanel.add(editProduct);
		northPanel.add(deleteProduct);
		add(northPanel, BorderLayout.NORTH);

		productTable.setModel(productTableModel);
		add(new JScrollPane(productTable), BorderLayout.CENTER);

		// Delete Button Action
		deleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				deleteProduct();
			}
		});
	}

	/**
	 * Add Product Object To JTable
	 * 
	 * @param discount
	 */
	private void addProduct(Product product) {
		productTableModel.addProduct(product);
		productTableModel.fireTableDataChanged();
	}

	/**
	 * Save Edited discount to file and reflect edited changes to Jtable
	 * 
	 * @param p
	 */
	private void editProduct(Product p) {

		int rowIndex = productTable.getSelectedRow();

		productTableModel.getListProducts().set(rowIndex, p);

		if (Controller.saveAllProducts(productTableModel.getListProducts())) {
			productTableModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.failure));
		}
	}

	/**
	 * Delete Product from file and Jtable
	 */
	private void deleteProduct() {

		int rowIndex = productTable.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.selectRow));
			return;
		}

		if (rowIndex >= 0) {
			productTableModel.removeProduct(rowIndex);

		}
		if (Controller.saveAllProducts(productTableModel.getListProducts())) {
			productTableModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.failure));
		}
	}

	/**
	 * Initialize Add/Edit Discount Panel
	 */
	private void initAddProductDialog() {
		productPanel.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
		initializeProductComboBox();
		// Setting default length and values of Text fields
		productCategoryCmbBox.setSize(Constants.DEFAULT_TEXTFIELD_SIZE, 1);
		prodName.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodDesc.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodQuant.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodPrice.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodBarCode.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodReorderQuant.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodOrderQuant.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		prodPrice.setText("0.00");
		prodReorderQuant.setText(Utility
				.getPropertyValue(Constants.prodReorderQuantDef));
		prodOrderQuant.setText(Utility
				.getPropertyValue(Constants.prodOrderQuantDef));

		// Adding Listeners to Text Fields
		prodReorderQuant.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (StringUtility.isEmpty(prodReorderQuant.getText())) {
					prodReorderQuant.setText(Utility
							.getPropertyValue(Constants.prodReorderQuantDef));
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		prodOrderQuant.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (StringUtility.isEmpty(prodOrderQuant.getText())) {
					prodOrderQuant.setText(Utility
							.getPropertyValue(Constants.prodOrderQuantDef));
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});
		// Category Field
		c.gridx = 0;
		c.gridy = 0;
		productPanel.add(productCategoryLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		productPanel.add(productCategoryCmbBox, c);
		// Product Name Field
		c.gridx = 0;
		c.gridy = 1;
		productPanel.add(productNameLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		productPanel.add(prodName, c);
		// Product Description Field
		c.gridx = 0;
		c.gridy = 2;
		productPanel.add(productDescriptionLabel, c);
		c.gridx = 1;
		c.gridy = 2;
		productPanel.add(prodDesc, c);
		// Product Quantity Field
		c.gridx = 0;
		c.gridy = 3;
		productPanel.add(productQtyLabel, c);
		c.gridx = 1;
		c.gridy = 3;
		productPanel.add(prodQuant, c);
		// Product Price Field
		c.gridx = 0;
		c.gridy = 4;
		productPanel.add(productPriceLabel, c);
		c.gridx = 1;
		c.gridy = 4;
		productPanel.add(prodPrice, c);
		// Product Barcode Field
		c.gridx = 0;
		c.gridy = 5;
		productPanel.add(barcodeLabel, c);
		c.gridx = 1;
		c.gridy = 5;
		productPanel.add(prodBarCode, c);
		// Product Reorder Threshold Qty Field
		c.gridx = 0;
		c.gridy = 6;
		productPanel.add(productReorderThresholdLabel, c);
		c.gridx = 1;
		c.gridy = 6;
		productPanel.add(prodReorderQuant, c);
		// Product Order Quantity Field
		c.gridx = 0;
		c.gridy = 7;
		productPanel.add(productOrderQtyLabel, c);
		c.gridx = 1;
		c.gridy = 7;
		productPanel.add(prodOrderQuant, c);
	}

	private void initializeProductComboBox() {
		/*
		 * Getting Combobox Ready
		 */
		// Getting Category Name - Code
		selectedCategory = null;
		category = fetchCategory.retrieveAll();
		allCategoryName = new String[category.size()];
		for (int i = 0; i < category.size(); i++) {
			allCategoryName[i] = category.get(i).getCategoryCode() + " - "
					+ category.get(i).getCategoryName();
		}

		// Binding Category Values to ComboBox
		productCategoryCmbBox.setModel(new DefaultComboBoxModel<>(
				allCategoryName));

		// Adding Listener to get Values
		productCategoryCmbBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String result = (String) productCategoryCmbBox
							.getSelectedItem();
					selectedCategory = result.split("-")[0].trim(); // Extracting
																	// Category
																	// Code
				}
			}
		});
		
		UIManager.put( "ComboBox.disabledBackground", new Color(212,212,210) );
		UIManager.put( "ComboBox.disabledForeground", Color.BLACK );

	}

	/**
	 * set product object property to Add / Edit Discount Panel
	 * 
	 * @param d
	 */
	private void setProductToProductDialogView(Product p) {
		initializeProductComboBox();
		if (p != null) {
			String categoryCmbBoxVal = p.getCategory().getCategoryCode()
					+ " - " + p.getCategory().getCategoryName();
			System.out.println("Box :" + categoryCmbBoxVal);
			productCategoryCmbBox.setSelectedItem(categoryCmbBoxVal);
			prodName.setText(p.getProductName());
			prodDesc.setText(p.getDescription());
			prodQuant.setText(Integer.toString(p.getQtyAvailable()));
			prodPrice.setText(Double.toString(p.getPrice()));
			prodBarCode.setText(Long.toString(p.getBarCode()));
			prodReorderQuant.setText(Integer.toString(p.getReorderQty()));
			prodOrderQuant.setText(Integer.toString(p.getOrderQty()));
		} else // reset to default value
		{
			prodName.setText("");
			prodDesc.setText("");
			prodQuant.setText("");
			prodBarCode.setText("");
			prodReorderQuant.setText(Utility
					.getPropertyValue(Constants.prodReorderQuantDef));
			prodOrderQuant.setText(Utility
					.getPropertyValue(Constants.prodOrderQuantDef));
		}
	}

	/**
	 * Add Product Popup
	 * 
	 * @param
	 */
	private void showAddProductDialog() {

		// Reset Product panel to default value
		setProductToProductDialogView(null);

		int result = JOptionPane.showConfirmDialog(mainView, productPanel,
				Utility.getPropertyValue(Constants.addProductBtn),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			String message = "";

			if (prodBarCode.getText().isEmpty()) {
				message = Constants.ALL_FIELDS_REQUIRED;
			}
			// Checking if Barcode exists
			else if (fetchProduct.getBarCodeProductMap().containsKey(
					Long.valueOf(prodBarCode.getText()))) {
				message = Utility.getPropertyValue(Constants.barcodeExists);
			} else {
				Product newProduct = createProductFromView(null);
				message = Controller.validateAndSaveProduct(newProduct);
			}
			if (message.equals(Constants.SUCCESS)) {
				JOptionPane.showMessageDialog(null, message);
				refreshProductTable();
			} else {
				JOptionPane.showMessageDialog(null, message, "Message",
						JOptionPane.ERROR_MESSAGE);
				showAddProductDialog();
			}
		}
	}

	/**
	 * It shows edit Discount Popup.
	 * 
	 * @param p
	 */
	private void showEditProductDialog(Product p) {

		// Fetch Product Values
		setProductToProductDialogView(p);
		productCategoryCmbBox.setEnabled(false);
		// Setting selected category since its not instantiated without change
		// event
		selectedCategory = p.getCategory().getCategoryCode();
		System.out.println("selected :" + selectedCategory);

		int result = JOptionPane.showConfirmDialog(mainView, productPanel,
				Utility.getPropertyValue(Constants.editProduct),
				JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			Product product = null;
			product = createProductFromView(p);
			String msg = Controller.validateProduct(product);
			if (msg.equals(Constants.SUCCESS))
				editProduct(product);
			else {
				JOptionPane.showMessageDialog(mainView, msg, "Message",
						JOptionPane.ERROR_MESSAGE);
				showEditProductDialog(product);
			}
		}
	}

	/**
	 * Create new Product Object from Add / Edit Discount Panel
	 * 
	 * @return
	 */
	private Product createProductFromView(Product newProduct) {

		if (newProduct == null) {
			newProduct = new Product();
		}
		newProduct.setProductName(prodName.getText());
		newProduct.setDescription(prodDesc.getText());
		newProduct.setQtyAvailable(Integer.parseInt(prodOrderQuant.getText()));
		newProduct.setPrice(new Double(prodPrice.getText()));
		newProduct.setBarCode(new Long(prodBarCode.getText()));
		newProduct.setReorderQty(Integer.parseInt(prodReorderQuant.getText()));
		newProduct.setOrderQty(Integer.parseInt(prodOrderQuant.getText()));
		newProduct.setCategory(fetchCategory.get(selectedCategory));
		return newProduct;
	}

	/**
	 * refreshes Jtable
	 */
	public void refreshProductTable() {
		productTableModel.clear();
		List<Product> products = Controller.getProducts();
		for (Product p : products) {
			addProduct(p);
		}
	}
}

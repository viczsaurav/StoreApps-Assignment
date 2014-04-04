package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.SwingUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.PublicCustomer;
import com.iss.storeApplication.domain.Transaction;
import com.iss.storeApplication.enums.DiscountType;
import com.iss.storeApplication.enums.MemberType;

/**
 * 
 * @author milan
 *
 */
public class BillingView extends JPanel {

	private MainView mainView;
	private JLabel barCodeLbl = new JLabel(
			Utility.getPropertyValue(Constants.barcode));
	private JFormattedTextField barCodeTextField = new JFormattedTextField(
			Utility.getProductBarcodeFormat());
	private JButton findProductBtn = new JButton(
			Utility.getPropertyValue(Constants.find));
	private JPanel findProductPanel = new JPanel();
	private JTable billingTable = new JTable();
	private BillingTableModel billingTableModel = new BillingTableModel(this);
	private JButton removeProductBtn = new JButton(
			Utility.getPropertyValue(Constants.remove));
	private Map<Long, Transaction> barCodeTransaction = new HashMap<Long, Transaction>();

	private JPanel totalPanel = new JPanel();
	private JLabel totalLbl = new JLabel(
			Utility.getPropertyValue(Constants.total));
	private JTextField totalTextField = new JTextField();

	private JPanel memberPanel = new JPanel();
	private JLabel memberTypeLbl = new JLabel(
			Utility.getPropertyValue(Constants.memberType));
	private JComboBox<MemberType> memberTypeCmbBox = new JComboBox<MemberType>();

	private JPanel memberIdPanel = new JPanel();
	private JLabel memberIdLbl = new JLabel(
			Utility.getPropertyValue(Constants.memberId));
	private JTextField memberIdTxtField = new JTextField();
	private JButton applyDiscountBtn = new JButton(
			Utility.getPropertyValue(Constants.applyDiscount));

	private JPanel discountPanel = new JPanel();
	private JLabel discountLbl = new JLabel(
			Utility.getPropertyValue(Constants.discount));
	private JTextField discountField = new JTextField();

	private JPanel billAmtPanel = new JPanel();
	private JLabel billAmtLbl = new JLabel(
			Utility.getPropertyValue(Constants.billAmt));
	private JTextField billAmtField = new JTextField();

	private JPanel generateBillPanel = new JPanel();
	private JButton generateBillBtn = new JButton(
			Utility.getPropertyValue(Constants.generateBill));
	private JButton resetBtn = new JButton(
			Utility.getPropertyValue(Constants.reset));

	private JPanel loyalityPanel = new JPanel();
	private JLabel loyalityLbl = new JLabel(
			Utility.getPropertyValue(Constants.loyalityEarned));
	private JFormattedTextField loyalityField = new JFormattedTextField(
			Utility.getIntegerFormat());

	private JPanel reedemPanel = new JPanel();
	private JLabel reedemLbl = new JLabel(
			Utility.getPropertyValue(Constants.reedemPoint));
	private JFormattedTextField reedemField = new JFormattedTextField(
			Utility.getIntegerFormat());

	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel(new GridLayout(9, 3));

	private final double dollarToPointValue = 0.1;
	private final double pointToDollarValue = 0.005;
	private Customer customer;

	private JTable transactionTable = new JTable();
	private TransactionTableModel transactionTableModel = new TransactionTableModel();
	private JScrollPane transactionTablePanel;

	public BillingView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;

		initFindProductPanel();

		initBillingTable();

		initTransactionTable();

		initTotalPanel();

		initMemberTypePanel();

		initMemberIdPanel();

		initDiscountPanel();

		initBillAmtPanel();

		initGenerateBillPanel();

		initLoyalityPanel();

		initReedemPanel();

		add(southPanel, BorderLayout.SOUTH);

		southPanel.remove(memberIdPanel);
		SwingUtility.refreshJpanel(southPanel);

	}

	/**
	 * Init Transation table which displays list of product customer is buying
	 */
	private void initTransactionTable() {
		transactionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		transactionTable.setModel(transactionTableModel);
		transactionTable.setPreferredScrollableViewportSize(new Dimension(600,
				400));
		transactionTable.setPreferredSize(new Dimension(600, 400));
		transactionTable.setSize(new Dimension(600, 400));
		transactionTablePanel = new JScrollPane(transactionTable);
		transactionTablePanel.setPreferredSize(new Dimension(600, 400));
		transactionTablePanel.setMaximumSize(new Dimension(600, 400));

	}

	/*
	 * Init Reedem point Panel
	 */
	private void initReedemPanel() {
		reedemField.setColumns(10);
		reedemField.setText("0");
		reedemPanel.add(reedemLbl);
		reedemPanel.add(reedemField);

	}

	/**
	 * Validate Reedem Point against Earned loyality for member
	 * 
	 * @return
	 */
	protected boolean validateReedemPoints() {

		Integer loyalityEarned = 0;
		Integer reedemPoint = 0;
		Double billAmt = 0.0;
		try {
			loyalityEarned = new Integer(loyalityField.getText());
		} catch (NumberFormatException e) {
			loyalityField.setText("0");

			return false;
		}
		try {
			reedemPoint = new Integer(reedemField.getText());
		} catch (NumberFormatException e) {

			reedemField.setText("0");

			return false;
		}
		try {
			billAmt = new Double(billAmtField.getText());
		} catch (NumberFormatException e) {

			billAmtField.setText("0");

			return false;
		}

		if (reedemPoint > loyalityEarned) {

			reedemField.setText("0");

			return false;
		} else {

			billAmt = billAmt - (pointToDollarValue * reedemPoint);
			billAmtField.setText(billAmt.toString());
			return true;
		}

	}

	/**
	 * Init loyality earned point
	 */
	private void initLoyalityPanel() {
		loyalityField.setColumns(10);
		loyalityField.setText("0");
		loyalityField.setEditable(false);
		loyalityField.setEnabled(false);
		loyalityPanel.add(loyalityLbl);
		loyalityPanel.add(loyalityField);

	}

	/**
	 * Init Generate Bill Panel
	 */
	private void initGenerateBillPanel() {
		generateBillBtn.setEnabled(false);
		generateBillPanel.add(generateBillBtn);
		generateBillPanel.add(resetBtn);
		southPanel.add(generateBillPanel);
		generateBillBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				generateBill();

			}
		});
	}

	/**
	 * Generate bill when user clicks generate bill. It saves bill and updates
	 * loyality earned of member customer
	 */
	protected void generateBill() {

		if (!validateReedemPoints()) {
			JOptionPane.showMessageDialog(mainView, Utility
					.getPropertyValue(Constants.msgCannotReedemMoreThanEarned));
			return;
		} else {
			// save Transactions
			Integer transactionId = Controller.getMaxTransactionId();
			transactionId++;

			List<Transaction> transactions = billingTableModel
					.getListtransactions();
			for (Transaction t : transactions) {
				t.setTransactionId(transactionId);
				if (customer == null)
					t.setCustomer(new PublicCustomer());
				else {
					t.setCustomer(customer);
				}
				t.setDateOfPurchase(new Date());
				if (!Controller.save(t)) {

					JOptionPane.showMessageDialog(mainView,
							Utility.getPropertyValue(Constants.failure));
					return;
				}

				// update product qty
				Product p = t.getProduct();
				int qty = p.getQtyAvailable() - t.getQtyPurchase();
				p.setQtyAvailable(qty);

				if (!Controller.editProduct(p)) {
					JOptionPane.showMessageDialog(mainView,
							Utility.getPropertyValue(Constants.failure));
					return;
				}
				transactionTableModel.addTranscation(t);
			}

			// add loyality earned from this transaction to member and save it.
			Object selectedItem = memberTypeCmbBox.getSelectedItem();

			if (MemberType.Member.equals(selectedItem)) {

				Integer loyality = customer.getLoyality();

				loyality = loyality - new Integer(reedemField.getText());

				Double billAmt = new Double(billAmtField.getText());
				Long loyalityEarned = Math.round(billAmt * dollarToPointValue);
				loyality = loyality + loyalityEarned.intValue();

				customer.setLoyality(loyality);
				if (!Controller.editCustomer(customer)) {
					JOptionPane.showMessageDialog(mainView,
							Utility.getPropertyValue(Constants.failure));
					return;
				}
			}

			// reset Form
			resetBill();

			// show transaction list
			JOptionPane.showMessageDialog(mainView, transactionTablePanel,
					Utility.getPropertyValue(Constants.billTitle),
					JOptionPane.INFORMATION_MESSAGE);

			// clear map
			barCodeTransaction.clear();
		}

	}

	/**
	 * init Bill Amt Panel
	 */
	private void initBillAmtPanel() {

		billAmtField.setColumns(10);
		billAmtField.setEditable(false);
		billAmtField.setEnabled(false);
		billAmtPanel.add(billAmtLbl);
		billAmtPanel.add(billAmtField);
		southPanel.add(billAmtPanel);
	}

	/**
	 * Init Discount Panel
	 */
	private void initDiscountPanel() {
		discountField.setColumns(10);
		discountField.setEditable(false);
		discountField.setEnabled(false);
		discountPanel.add(discountLbl);
		discountPanel.add(discountField);
		southPanel.add(discountPanel);
	}

	/**
	 * Init Member Panel
	 */
	private void initMemberIdPanel() {
		memberIdTxtField.setColumns(15);
		memberIdPanel.add(memberIdLbl);
		memberIdPanel.add(memberIdTxtField);
		memberIdPanel.add(applyDiscountBtn);
		southPanel.add(memberIdPanel, BorderLayout.CENTER);

		applyDiscountBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				applyMemberDiscount();

			}
		});
		memberIdTxtField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				applyMemberDiscount();

			}
		});
	}

	/**
	 * Apply Discount to customer.
	 */
	protected void applyMemberDiscount() {
		String memberId = memberIdTxtField.getText();
		if (StringUtility.isEmpty(memberId)) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.msgEnterMemId));
			return;
		}
		Customer customer = Controller.getCustomer(memberId);
		if (customer == null) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.msgMemNotFound));
			return;
		}
		if (customer.getLoyality() == -1)// first time purchase
		{
			loyalityField.setText("0");// 1st time customer

			Discount d = Controller.getMemberFirstDiscount();
			if (d == null) {
				d = Controller.getMaxMemberDiscount();
			}
			setDiscountToTextField(d);
		} else {
			loyalityField.setText(customer.getLoyality().toString());// 1st
																		// time
																		// customer
			Discount d = Controller.getMaxMemberDiscount();
			setDiscountToTextField(d);
		}
		calculateMemberBillAmount();
		this.customer = customer;

	}

	/**
	 * set discount to discount Field
	 * 
	 * @param d
	 */
	private void setDiscountToTextField(Discount d) {
		if (d == null)// not discount means 0%
		{
			discountField.setText("0.0");
		} else {
			discountField.setText(d.getDiscount().toString());
		}

	}

	/**
	 * Init Member Type Panel
	 */
	private void initMemberTypePanel() {
		memberTypeCmbBox.setModel(new DefaultComboBoxModel<>(MemberType
				.values()));
		memberPanel.add(memberTypeLbl);
		memberPanel.add(memberTypeCmbBox);
		southPanel.add(memberPanel, BorderLayout.SOUTH);
		memberTypeCmbBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					System.out.println(((JComboBox<DiscountType>) e.getSource())
							.getSelectedItem());
					Object selectedItem = ((JComboBox<DiscountType>) e
							.getSource()).getSelectedItem();
					if (MemberType.Public.equals(selectedItem)) {
						southPanel.remove(memberIdPanel);
						southPanel.remove(loyalityPanel);
						southPanel.remove(reedemPanel);

						calculatePublicBillAmount();

					} else {
						southPanel.add(memberIdPanel, 2);
						southPanel.add(loyalityPanel, 3);
						southPanel.add(reedemPanel, 5);

					}
					SwingUtility.refreshJpanel(southPanel);
				}

			}
		});
		memberTypeCmbBox.setSelectedIndex(0);

	}

	/**
	 * Calculate bill for non member customer
	 */
	protected void calculatePublicBillAmount() {
		Discount d = Controller.getMaxPublicDiscount();
		Double discount = 0.0;
		if (d != null) {
			discount = d.getDiscount();
		}
		Double billAmount = 0.0;
		try {
			billAmount = new Double(totalTextField.getText());
		} catch (NumberFormatException e) {
			totalTextField.setText("0");
		}
		billAmount = billAmount - (billAmount * discount) / 100.0;
		discountField.setText(discount.toString());
		billAmtField.setText(billAmount.toString());

	}

	/**
	 * Init Total Panel
	 */
	private void initTotalPanel() {
		totalTextField.setEditable(false);
		totalTextField.setEnabled(false);
		totalTextField.setColumns(10);
		totalPanel.add(totalLbl);
		totalPanel.add(totalTextField);
		southPanel.add(totalPanel, BorderLayout.SOUTH);

		totalTextField.getDocument().addDocumentListener(
				new DocumentListener() {

					public void insertUpdate(DocumentEvent e) {
						calculate();
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub

					}

				});
	}

	/**
	 * calculate bill Amt
	 */
	public void calculate() {
		if (billingTable.getRowCount() > 0)
			generateBillBtn.setEnabled(true);
		Object selectedItem = memberTypeCmbBox.getSelectedItem();
		if (MemberType.Public.equals(selectedItem)) {
			calculatePublicBillAmount();
		} else {
			calculateMemberBillAmount();
		}
	}

	/**
	 * Calculate Bill Amt for member customer
	 */
	protected void calculateMemberBillAmount() {

		Double discount = 0.0;
		Double billAmount = 0.0;
		try {
			discount = new Double(discountField.getText());
			billAmount = new Double(totalTextField.getText());
		} catch (NumberFormatException e) {
			totalTextField.setText("0");
			discountField.setText("0");
		}
		billAmount = billAmount - (billAmount * discount) / 100.0;

		billAmtField.setText(billAmount.toString());

	}

	/**
	 * Find product from barcod and display to table
	 */
	private void initFindProductPanel() {
		barCodeTextField.setColumns(4);

		findProductPanel.setLayout(new FlowLayout());
		findProductPanel.add(barCodeLbl);
		findProductPanel.add(barCodeTextField);

		findProductPanel.add(findProductBtn);
		findProductPanel.add(removeProductBtn);
		findProductBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				findProduct(barCodeTextField.getText());

			}
		});

		barCodeTextField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				findProduct(barCodeTextField.getText());

			}
		});

		removeProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				removeProductFromBillingTable();
			}
		});

		add(findProductPanel, BorderLayout.NORTH);

	}

	/**
	 * Init billing table
	 */
	private void initBillingTable() {
		billingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		billingTable.setModel(billingTableModel);
		billingTable
				.setPreferredScrollableViewportSize(new Dimension(600, 250));
		billingTable.setPreferredSize(new Dimension(600, 250));
		billingTable.setSize(new Dimension(600, 250));
		JScrollPane sp = new JScrollPane(billingTable);
		sp.setPreferredSize(new Dimension(600, 250));
		sp.setMaximumSize(new Dimension(600, 250));
		centerPanel.add(sp);
		add(centerPanel, BorderLayout.CENTER);

	}

	/**
	 * Remove product from Billing table
	 */
	protected void removeProductFromBillingTable() {
		int rowIndex = billingTable.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.selectRow));
			return;
		}

		if (rowIndex >= 0) {
			if (barCodeTransaction.containsValue(billingTableModel
					.getListtransactions().get(rowIndex))) {
				barCodeTransaction.remove(billingTableModel
						.getListtransactions().get(rowIndex).getProduct()
						.getBarCode());
			}
			billingTableModel.removeTranscation(rowIndex);

		}
		billingTableModel.fireTableDataChanged();
		calculateTotal();

	}

	/**
	 * Find product from barcode
	 * 
	 * @param barcode
	 */
	protected void findProduct(String barcode) {
		if (StringUtility.isEmpty(barcode)) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.barcodeNotNull));
			return;
		}
		if (barCodeTransaction.containsKey(new Long(barcode))) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.msgChgQty));
			return;
		}
		Product p = Controller.getProduct(new Long(barcode));
		if (p == null) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.productNotFound));
			return;
		}
		System.out.println(p.getCommaSeperatedValue());
		Transaction t = new Transaction();
		t.setProduct(p);
		t.setQtyPurchase(1);
		addProductToBillingTable(t);
		barCodeTransaction.put(new Long(barcode), t);
		calculateTotal();
	}

	/**
	 * add product to billing table
	 * 
	 * @param t
	 */
	private void addProductToBillingTable(Transaction t) {
		billingTableModel.addTranscation(t);
		billingTableModel.fireTableDataChanged();
	}

	/**
	 * Calculate Total Amt
	 */
	public void calculateTotal() {
		List<Transaction> transactions = billingTableModel
				.getListtransactions();
		Double total = 0.0;
		for (Transaction t : transactions) {
			total += (t.getProduct().getPrice() * t.getQtyPurchase());
		}
		totalTextField.setText(total.toString());
	}

	/**
	 * Reset Bill
	 */
	public void resetBill() {
		customer = null;
		barCodeTextField.setText("");
		billingTableModel.clear();
		billingTableModel.fireTableDataChanged();
		totalTextField.setText("0");
		memberTypeCmbBox.setSelectedIndex(0);
		billAmtField.setText("0");
		generateBillBtn.setEnabled(false);

	}

}

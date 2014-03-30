package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

	public BillingView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;

		initFindProductPanel();

		initBillingTableModel();

		initTotalPanel();

		initMemberTypePanel();

		initMemberIdPanel();

		initDiscountPanel();

		initBillAmtPanel();

		initGeneratePanel();

		initLoyalityPanel();

		initReedemPanel();

		add(southPanel, BorderLayout.SOUTH);

		southPanel.remove(memberIdPanel);
		SwingUtility.refreshJpanel(southPanel);

	}

	private void initReedemPanel() {
		reedemField.setColumns(10);
		reedemField.setText("0");
		reedemPanel.add(reedemLbl);
		reedemPanel.add(reedemField);
		/*
		 * reedemField.getDocument().addDocumentListener(new DocumentListener()
		 * {
		 * 
		 * public void insertUpdate(DocumentEvent e) { reedemPoints(); }
		 * 
		 * @Override public void changedUpdate(DocumentEvent e) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void removeUpdate(DocumentEvent e) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * });
		 */
		reedemField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					reedemPoints();
				}

			}
		});

		reedemField.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				reedemPoints();

			}

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	protected void reedemPoints() {

		Integer loyalityEarned = 0;
		Integer reedemPoint = 0;
		Double billAmt = 0.0;
		try {
			loyalityEarned = new Integer(loyalityField.getText());
		} catch (NumberFormatException e) {
			loyalityField.setText("0");

			return;
		}
		try {
			reedemPoint = new Integer(reedemField.getText());
		} catch (NumberFormatException e) {

			reedemField.setText("0");

			return;
		}
		try {
			billAmt = new Double(billAmtField.getText());
		} catch (NumberFormatException e) {

			billAmtField.setText("0");

			return;
		}

		if (reedemPoint > loyalityEarned) {

			reedemField.setText("0");
			JOptionPane.showMessageDialog(mainView, Utility
					.getPropertyValue(Constants.msgCannotReedemMoreThanEarned));
			return;
		} else {
			billAmt = billAmt - (pointToDollarValue * reedemPoint);
			billAmtField.setText(billAmt.toString());
		}

	}

	private void initLoyalityPanel() {
		loyalityField.setColumns(10);
		loyalityField.setText("0");
		loyalityField.setEditable(false);
		loyalityField.setEnabled(false);
		loyalityPanel.add(loyalityLbl);
		loyalityPanel.add(loyalityField);

	}

	private void initGeneratePanel() {
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

	protected void generateBill() {
		//update member loyality point
		Object selectedItem = memberTypeCmbBox.getSelectedItem();
		
		if (MemberType.Member.equals(selectedItem)) {
			
			Integer loyality=customer.getLoyality();
			
			loyality=loyality-new Integer(reedemField.getText());
			customer.setLoyality(loyality);
			if(!Controller.editCustomer(customer))
			{
				JOptionPane.showMessageDialog(mainView, Utility.getPropertyValue(Constants.failure));
				return;
			}
		}

	}

	private void initBillAmtPanel() {

		billAmtField.setColumns(10);
		billAmtField.setEditable(false);
		billAmtField.setEnabled(false);
		billAmtPanel.add(billAmtLbl);
		billAmtPanel.add(billAmtField);
		southPanel.add(billAmtPanel);
	}

	private void initDiscountPanel() {
		discountField.setColumns(10);
		discountField.setEditable(false);
		discountField.setEnabled(false);
		discountPanel.add(discountLbl);
		discountPanel.add(discountField);
		southPanel.add(discountPanel);
	}

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

	protected void applyMemberDiscount() {
		String memberId = memberIdTxtField.getText();
		if (StringUtility.isEmpty(memberId)) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.msgEnterMemId));
			return;
		}
		Customer member = Controller.getCustomer(memberId);
		if (member == null) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.msgMemNotFound));
			return;
		}
		if (member.getLoyality() == -1)// first time purchase
		{
			loyalityField.setText("0");// 1st time customer

			Discount d = Controller.getMemberFirstDiscount();
			if (d == null) {
				d = Controller.getMaxMemberDiscount();
			}
			setDiscountToTextField(d);
		} else {
			loyalityField.setText(member.getLoyality().toString());// 1st
																		// time
																		// customer
			Discount d = Controller.getMaxMemberDiscount();
			setDiscountToTextField(d);
		}
		calculateMemberBillAmount();
		this.customer=member;

	}

	private void setDiscountToTextField(Discount d) {
		if (d == null)// not discount means 0%
		{
			discountField.setText("0.0");
		} else {
			discountField.setText(d.getDiscount().toString());
		}

	}

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
	 * Recalculate when total has changed
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
				removeProductFromTable();
			}
		});

		add(findProductPanel, BorderLayout.NORTH);

	}

	private void initBillingTableModel() {
		billingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		billingTable.setModel(billingTableModel);
		billingTable
				.setPreferredScrollableViewportSize(new Dimension(600, 400));
		billingTable.setPreferredSize(new Dimension(600, 400));
		billingTable.setSize(new Dimension(600, 400));
		JScrollPane sp = new JScrollPane(billingTable);
		sp.setPreferredSize(new Dimension(600, 400));
		sp.setMaximumSize(new Dimension(600, 400));
		centerPanel.add(sp);
		add(centerPanel, BorderLayout.CENTER);

	}

	protected void removeProductFromTable() {
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

	}

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
		addTransactionToTable(t);
		barCodeTransaction.put(new Long(barcode), t);
		calculateTotal();
	}

	private void addTransactionToTable(Transaction t) {
		billingTableModel.addTranscation(t);
		billingTableModel.fireTableDataChanged();
	}

	public void calculateTotal() {
		List<Transaction> transactions = billingTableModel
				.getListtransactions();
		Double total = 0.0;
		for (Transaction t : transactions) {
			total += (t.getProduct().getPrice() * t.getQtyPurchase());
		}
		totalTextField.setText(total.toString());
	}
	
	public void resetBill()
	{
		customer=null;
	}

}

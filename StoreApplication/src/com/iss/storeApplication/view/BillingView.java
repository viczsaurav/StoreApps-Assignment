package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
	private JTable billingTabble = new JTable();
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
	private JTextField loyalityField = new JTextField();

	private JPanel reedemPanel = new JPanel();
	private JLabel reedemLbl = new JLabel(
			Utility.getPropertyValue(Constants.reedemPoint));
	private JTextField reedemField = new JTextField();

	private JPanel centerPanel = new JPanel();
	private JPanel southPanel = new JPanel(new GridLayout(9, 3));

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
		reedemPanel.add(reedemLbl);
		reedemPanel.add(reedemField);

	}

	private void initLoyalityPanel() {
		loyalityField.setColumns(10);
		loyalityField.setEditable(false);
		loyalityField.setEnabled(false);
		loyalityPanel.add(loyalityLbl);
		loyalityPanel.add(loyalityField);

	}

	private void initGeneratePanel() {

		generateBillPanel.add(generateBillBtn);
		generateBillPanel.add(resetBtn);
		southPanel.add(generateBillPanel);
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

	public void calculate() {
		Object selectedItem = memberTypeCmbBox.getSelectedItem();
		if (MemberType.Public.equals(selectedItem)) {
			calculatePublicBillAmount();
		} else {
			calculateMemberBillAmount();
		}
	}

	protected void calculateMemberBillAmount() {
		// TODO Auto-generated method stub

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
		billingTabble.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		billingTabble.setModel(billingTableModel);
		billingTabble
				.setPreferredScrollableViewportSize(new Dimension(600, 400));
		billingTabble.setPreferredSize(new Dimension(600, 400));
		billingTabble.setSize(new Dimension(600, 400));
		JScrollPane sp = new JScrollPane(billingTabble);
		sp.setPreferredSize(new Dimension(600, 400));
		sp.setMaximumSize(new Dimension(600, 400));
		centerPanel.add(sp);
		add(centerPanel, BorderLayout.CENTER);

	}

	protected void removeProductFromTable() {
		int rowIndex = billingTabble.getSelectedRow();
		if (rowIndex == -1) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.selectRow));
			return;
		}

		if (rowIndex >= 0) {
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

}

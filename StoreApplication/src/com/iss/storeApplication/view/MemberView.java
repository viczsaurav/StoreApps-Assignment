package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.Product;



/**
 * 
 * @author Siva
 *
 */
public class MemberView extends JPanel{
	
	private MainView mainView;
	private String selectedMember;
	public MemberView(MainView mainView) {
		super(new BorderLayout());
		this.mainView = mainView;
		addMemberBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				//addBtnClicked(event);

			}
		});
		editMemberBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
			//	editBtnClicked(event);

			}
		});
		
		//init top panel
		initTopPanel();
		//init center panel
		initCenterPanel();	
		//Refresh Table
		refreshMemberTable();
	}
	//Top and center panels
	private JPanel topPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private final JPanel addMemberPanel = new JPanel();	
	// add Edit Delete
			private JButton deleteMemberBtn = new JButton(Utility.getPropertyValue(Constants.deleteMember));
			private JButton addMemberBtn = new JButton(
					Utility.getPropertyValue(Constants.addMemberBtn));
			private JButton editMemberBtn = new JButton(
					Utility.getPropertyValue(Constants.editMemberBtn));
	
	//Member Labels
	private final JLabel memberNameLabel = new JLabel(
			Utility.getPropertyValue(Constants.memberName));
	
	private final JLabel memberIdLabel = new JLabel(
			Utility.getPropertyValue(Constants.memberId));
	private final JLabel loyalityLabel = new JLabel(
			Utility.getPropertyValue(Constants.loyality));
	//Member Text Fields
	private final JTextField memberNameField = new JTextField(20);
	private final JTextField memberIdField = new JTextField(20);
	private final JTextField loyalityField = new JTextField(20);
	
	//Table
	private JTable memberTable = new JTable();
	private MemberModel memberModel=new MemberModel();
	

	
	
	private void initCenterPanel() {

		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
     
		memberTable.setModel(memberModel);
		memberTable.setPreferredScrollableViewportSize(new Dimension(600, 400));
		memberTable.setPreferredSize(new Dimension(600, 400));
		memberTable.setSize(new Dimension(600, 400));
		JScrollPane sp = new JScrollPane(memberTable);
		sp.setPreferredSize(new Dimension(600, 400));
		sp.setMaximumSize(new Dimension(600, 400));
		centerPanel.add(sp);
		add(centerPanel, BorderLayout.CENTER);
	}



	private void initTopPanel() {
	   topPanel.add(addMemberBtn);
	   topPanel.add(editMemberBtn);
	   topPanel.add(deleteMemberBtn);
	   addMemberBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				addBtnClicked(event);

			}
		});
	   add(topPanel, BorderLayout.NORTH);
	   
	   initAddMemberDialog();
	   editMemberBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
			editBtnClicked(event);

			}
		});
	
	}

	/**
	 * Edit Button Clicked. Edit Popup  will show.
	 * 
	 * @param event
	 */
	protected void editBtnClicked(ActionEvent event) {
		int row = memberTable.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.selectRow));
			return;
		}

		MemberCustomer m = (MemberCustomer) memberModel.getMembers().get(row);
		showEditMemberDialog(m);
	}
	
	private void showEditMemberDialog(MemberCustomer m) {
		// Fetch Product Values
				setMemberToMemberDialogView(m);
				
				// Setting selected category since its not instantiated without change
				// event
				selectedMember = m.getMemberId();
				System.out.println("selected :" + selectedMember);

				int result = JOptionPane.showConfirmDialog(mainView, addMemberPanel,
						Utility.getPropertyValue(Constants.editMember),
						JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					MemberCustomer member = null;
					member = createMemberFromView(m);
					String msg = Controller.validateMember(m);
					if (msg.equals(Constants.SUCCESS))
						editMember(member);
					else {
						JOptionPane.showMessageDialog(mainView, msg, "Message",
								JOptionPane.ERROR_MESSAGE);
						showEditMemberDialog(member);
					}
				}
		
	}


	/**
	 * Save Edited discount to file and reflect edited changes to Jtable
	 * 
	 * @param p
	 */
	private void editMember(MemberCustomer m) {

		int rowIndex = memberTable.getSelectedRow();

		memberModel.getMembers().set(rowIndex, m);

		if (Controller.saveAllMembers(memberModel.getMembers())) {
			memberModel.fireTableDataChanged();
		} else {
			JOptionPane.showMessageDialog(mainView,
					Utility.getPropertyValue(Constants.failure));
		}
	}
	private void setMemberToMemberDialogView(MemberCustomer m) {
		if (m != null) {
			
			memberNameField.setText(m.getMemberName());
			memberIdField.setText(m.getMemberId());
			loyalityField.setText((m.getLoyality().toString()));
		} else // reset to default value
		{
			memberNameField.setText("");
			memberIdField.setText("");
			loyalityField.setText(Utility
					.getPropertyValue(Constants.defaultLoyality));
			
			
		}
		
	}



	/**
	 * Initialize Add/Edit Member Panel
	 */
	private void initAddMemberDialog() {
		addMemberPanel.setLayout(new GridLayout(3,2));

		memberNameField.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		
		memberIdField.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		
		loyalityField.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		loyalityField.setText(Utility.getPropertyValue(Constants.defaultLoyality));
		loyalityField.setEnabled(false);
	
				addMemberPanel.add(memberNameLabel);
			
				addMemberPanel.add(memberNameField);
		// Member Id Field
			
				addMemberPanel.add(memberIdLabel);
			
				addMemberPanel.add(memberIdField);
		// Member Name Field
	
				addMemberPanel.add(loyalityLabel);

				addMemberPanel.add(loyalityField);
				
				
	}
	
	protected void addBtnClicked(ActionEvent event) {

		showAddMemberDialog();
	}
	
	

	private void showAddMemberDialog() {
              
	/*	int result = JOptionPane.showConfirmDialog(mainView, addMemberPanel,
						Utility.getPropertyValue(Constants.addMemberBtn),
						JOptionPane.OK_CANCEL_OPTION);
		
		                if (result == JOptionPane.OK_OPTION) {
		                	String message = "";
		                }*/
		setMemberToMemberDialogView(null);
		int result = JOptionPane.showConfirmDialog(mainView, addMemberPanel, Utility.getPropertyValue(Constants.addMemberBtn), JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			String message = "";
			
			if ((memberNameField.getText().isEmpty())|| (memberIdField.getText().isEmpty())){
				message=Constants.ALL_FIELDS_REQUIRED;
			}else {
				MemberCustomer newMember = createMemberFromView(null);
				message = Controller.validateAndSaveMember(newMember);
			}
			if (message.equals(Constants.SUCCESS)) {
				JOptionPane.showMessageDialog(null, message);
				refreshMemberTable();
			} else {
				JOptionPane.showMessageDialog(null, message, "Message",
						JOptionPane.ERROR_MESSAGE);
				showAddMemberDialog();
			}
		}
	
	}


	private MemberCustomer createMemberFromView(MemberCustomer newMember) {
		if (newMember==null){
			newMember=new MemberCustomer();
		}
			newMember.setMemberName(memberNameField.getText());
			newMember.setMemberId(memberIdField.getText());
			newMember.setLoyality(-1);
			
		
		return newMember;
	}



	/**
	 * refreshes Jtable
*/
	public void refreshMemberTable() {
	     memberModel.clear();
		List<Customer> memberCustomers = Controller.getMemberCustomers();
		for (Customer p : memberCustomers) {
			addMember((MemberCustomer)p);
		}
	}	 
	
	/**
	 * Add Customer Object To JTable
	 * 
	 * @param discount
	 */
	private void addMember(MemberCustomer memberCustomer) {
		memberModel.addMember(memberCustomer);
		memberModel.fireTableDataChanged();
	}
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(0, 36));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

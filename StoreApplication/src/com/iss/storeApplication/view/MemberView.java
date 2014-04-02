package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
	/*
	public MemberView() {
		add(new Button("MemberView"));
	}
	*/
	private MainView mainView;
	
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
	}
	//Top and center panels
	private JPanel topPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private final JPanel addMemberPanel = new JPanel();	
	// add Edit Delete
			private JButton deleteMemberBtn = new JButton("Delete Member");
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
	   topPanel.add(addMemberBtn, BorderLayout.NORTH);
	   topPanel.add(editMemberBtn, BorderLayout.NORTH);
	   topPanel.add(deleteMemberBtn, BorderLayout.NORTH);
	   addMemberBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				addBtnClicked(event);

			}
		});
	}

	
	private void initAddMemberDialog() {
		addMemberPanel.setLayout(new GridBagLayout());
		final GridBagConstraints c = new GridBagConstraints();
        
		memberNameField.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		memberNameField.setText("");
		memberIdField.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		memberIdField.setText("");
		loyalityField.setColumns(Constants.DEFAULT_TEXTFIELD_SIZE);
		loyalityField.setText("");
	
		// Member Name Field
				c.gridx = 0;
				c.gridy = 1;
				addMemberPanel.add(memberNameLabel, c);
				c.gridx = 1;
				c.gridy = 1;
				addMemberPanel.add(memberNameField, c);
		// Member Id Field
				c.gridx = 0;
				c.gridy = 1;
				addMemberPanel.add(memberIdLabel, c);
				c.gridx = 1;
				c.gridy = 1;
				addMemberPanel.add(memberIdField, c);
		// Member Name Field
				c.gridx = 0;
				c.gridy = 1;
				addMemberPanel.add(loyalityLabel, c);
				c.gridx = 1;
				c.gridy = 1;
				addMemberPanel.add(loyalityField, c);
	
	}
	
	protected void addBtnClicked(ActionEvent event) {

		showAddMemberDialog();
	}
	
	

	private void showAddMemberDialog() {
		addMemberPanel.setLayout(new GridBagLayout());                
		int result = JOptionPane.showConfirmDialog(mainView, addMemberPanel,
						Utility.getPropertyValue(Constants.addMemberBtn),
						JOptionPane.OK_CANCEL_OPTION);
		
		                if (result == JOptionPane.OK_OPTION) {
		                	
		                }
	
	
	}


	/**
	 * refreshes Jtable
	 */
	public void refreshMemberTable() {
	     memberModel.clear();
		List<MemberCustomer> memberCustomers = Controller.getMemberCustomers();
		for (MemberCustomer p : memberCustomers) {
			addMember(p);
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

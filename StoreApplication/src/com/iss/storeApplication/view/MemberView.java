package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;

/**
 * 
 * @author milan
 *
 */
public class MemberView extends JPanel{

	public MemberView() {
		add(new Button("MemberView"));
	}
	/*
	private MainView mainView;
	//Top and center panels
	private JPanel topPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	
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
	private MemberModel MemberModel=new MemberModel();
	

	
	
	private void initCenterPanel() {

		memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		memberTable.setModel(MemberModel);
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
	   
	}


*/
}

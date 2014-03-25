package com.iss.storeApplication.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.domain.Category;


/**
 * @author luke
 */

public class CategoryView extends JPanel {


	public JTable table;
	DefaultTableModel model;

	public CategoryView() {
		setBounds(100, 100, 580, 242);
		setLayout(new BorderLayout());
		initialCategory();

	}

	private void initialCategory() {
		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		// some setting on panel
		JPanel north = new JPanel(new BorderLayout());
		JButton btnGetRowSelected = new JButton(Constants.ADDCATEGORY_BTN);

		// Table
		table = new JTable();
		scrollPane.setViewportView(table);

		north.add(btnGetRowSelected, BorderLayout.EAST);
		//define add button function
		btnGetRowSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTextField codeField = new JTextField();
				JTextField namefield = new JTextField();

				Object[] message = {Constants.CATEGORYID_LABEL, codeField, 
									Constants.CATEGORYNAME_LABEL,namefield};


				int option = JOptionPane.showConfirmDialog(null, message,
						Constants.ADDCATEGORY_BTN, JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String categoryCode = codeField.getText();
					String categoryName = namefield.getText();
					System.out.println("categoryCode:" + categoryCode);
					System.out.println("categoryName:" + categoryName);
					freshCategory(model);
				}

			}
		});
		
		
		btnGetRowSelected.setBounds(224, 149, 131, 23);
		add(north, BorderLayout.NORTH);
		add(scrollPane);

		//define Model for Table
		model = new DefaultTableModel() {
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

		table.setModel(model);

		model.addColumn(Constants.CATEGORYID_LABEL);
		model.addColumn(Constants.CATEGORYNAME_LABEL);

//get and display category list 
		freshCategory(model);
	}

	private void freshCategory(DefaultTableModel model) {

		List<Category> cat1 = new ArrayList<Category>();

		// cat1 =
		// CommonDao.retrieveAllRecordArray("Category.dat",Category.class);
		// Data Row
		int i = 0;
		for (Category entry : cat1) {
			model.addRow(new Object[0]);
			model.setValueAt(entry.getCategoryCode(), i, 0);
			model.setValueAt(entry.getCategoryName(), i, 1);
			i++;
		}

	}

}

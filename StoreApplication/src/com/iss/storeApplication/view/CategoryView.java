package com.iss.storeApplication.view;

import java.awt.BorderLayout;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.iss.storeApplication.business.CategoryService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.dao.CategoryDao;
import com.iss.storeApplication.domain.Category;

/**
 * @author luke
 */

public class CategoryView extends JPanel {

	List<Category> cat1;
	CategoryView objectCat = this;
	public JTable table;
	DefaultTableModel model;
	private JPanel categoryPanel;
	JScrollPane scrollPane;

	public CategoryView() {
		setBounds(100, 100, 580, 242);
		setLayout(new BorderLayout());
		initialCategory();

	}

	/**
	 * initial Category
	 */
	private void initialCategory() {
		scrollPane = new JScrollPane();
		categoryPanel = new JPanel();
		// ScrollPane for Table
		scrollPane.setBounds(33, 41, 494, 90);
		// some setting on panel
		JButton btnAdd = new JButton(Constants.ADDCATEGORY_BTN);
		JButton btnDelete = new JButton("Delete");
		JButton btnEdit = new JButton("Edit");
		// Table
		table = new JTable();
		scrollPane.setViewportView(table);

		categoryPanel.add(btnAdd, BorderLayout.CENTER);
		categoryPanel.add(btnDelete, BorderLayout.CENTER);
		categoryPanel.add(btnEdit, BorderLayout.CENTER);
		// define add button function
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JTextField codeField = new JTextField();
				JTextField namefield = new JTextField();

				codeField.addCaretListener(new CaretListener() {

					@Override
					public void caretUpdate(CaretEvent arg0) {
						System.out.println(arg0.toString());

					}
				});

				Object[] message = { Constants.CATEGORYID_LABEL, codeField,
						Constants.CATEGORYNAME_LABEL, namefield };
				boolean isdone = true;
				int option;
				while (isdone) {
					option = JOptionPane.showConfirmDialog(null, message,
							Constants.ADDCATEGORY_BTN,
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						String categoryCode = codeField.getText();
						String categoryName = namefield.getText();
						System.out.println("categoryCode:" + categoryCode);
						System.out.println("categoryName:" + categoryName);
						Category cat = new Category();
						cat.setCategoryCode(categoryCode);
						cat.setCategoryName(categoryName);
						String result = Controller.validateAndSaveCategory(cat);
						if (Constants.SUCCESS.equals(result)) {
							freshCategory(model);
							isdone = false;
						} else {
							JOptionPane.showMessageDialog(null, result);
						}
					}else{
						isdone = false;
					}
				}
			}
		});

		// define delete button function
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex >= 0) {
					cat1.remove(rowIndex);
				}else{
					JOptionPane.showMessageDialog(null,"please select one row!");
				}
				if (Controller.saveAllCategory(cat1)) {
					freshCategory(model);
				} else {
					JOptionPane.showMessageDialog(null,
							Utility.getPropertyValue(Constants.failure));
				}
			}
		});
		// define edit button function
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex >= 0) {
					Category categoriesItem =  cat1.get(rowIndex);
	
					JTextField codeField = new JTextField(categoriesItem.getCategoryCode());
					JTextField namefield = new JTextField(categoriesItem.getCategoryName());

					Object[] message = { Constants.CATEGORYID_LABEL, codeField,
							Constants.CATEGORYNAME_LABEL, namefield };
					boolean isdone = true;
					int option;
					while (isdone) {
						option = JOptionPane.showConfirmDialog(null, message,
								Constants.ADDCATEGORY_BTN,
								JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
							String categoryCode = codeField.getText();
							String categoryName = namefield.getText();
							System.out.println("categoryCode:" + categoryCode);
							System.out.println("categoryName:" + categoryName);
							Category cat = new Category();
							cat.setCategoryCode(categoryCode);
							cat.setCategoryName(categoryName);
							String result = Controller.validateAndSaveCategory(cat);
							if (Constants.SUCCESS.equals(result)) {
								cat1.remove(rowIndex);
								cat1.add(rowIndex, cat);
								isdone = false;
							} 
							else if(Constants.CATEGORY_EXIST.equals(result)){
								if(categoryCode.equals(categoriesItem.getCategoryCode())){
									categoriesItem.setCategoryName(categoryName);
									isdone = false;
								}else{
									JOptionPane.showMessageDialog(null, result);
								}
							}
							else {
								JOptionPane.showMessageDialog(null, result);
							}
						}else{
							isdone = false;
							
						}
					}			
					
				}else{
					JOptionPane.showMessageDialog(null,"please select one row!");
				}
				if (Controller.saveAllCategory(cat1)) {
					freshCategory(model);
				} else {
					JOptionPane.showMessageDialog(null,
							Utility.getPropertyValue(Constants.failure));
				}
			}
		});
		btnAdd.setBounds(224, 149, 131, 23);
		add(categoryPanel, BorderLayout.NORTH);
		add(scrollPane);
		// get and display category list
		freshCategory(model);
	}

	private void freshCategory(DefaultTableModel model) {
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

		// align category field in center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);

		table.setModel(model);

		model.addColumn(Constants.CATEGORYID_LABEL);
		model.addColumn(Constants.CATEGORYNAME_LABEL);

		CategoryDao categoryDao = new CategoryDao();
		cat1 = categoryDao.retrieveAll();

		// Putting Values in the Category Table
		int i = 0;
		for (Category entry : cat1) {
			model.addRow(new Object[0]);
			model.setValueAt(entry.getCategoryCode(), i, 0);
			model.setValueAt(entry.getCategoryName(), i, 1);
			i++;
		}

	}

}

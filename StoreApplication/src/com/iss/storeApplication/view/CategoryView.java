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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

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
		//initial table
		table = new JTable();
		scrollPane.setViewportView(table);
		// initial model for table of category
		model  = new DefaultTableModel() {
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
		
		model.addColumn(Constants.categoryCode);
		model.addColumn(Constants.categoryName);

		// ScrollPane for Table
		scrollPane.setBounds(33, 41, 494, 90);
		// some setting on panel
		JButton btnAdd = new JButton(Constants.addCategoryBtn);
		JButton btnDelete = new JButton(Constants.categoryDeleteCategory);
		JButton btnEdit = new JButton(Constants.categoryEditCategory);
		// Table
		categoryPanel.add(btnAdd, BorderLayout.CENTER);
		categoryPanel.add(btnEdit, BorderLayout.CENTER);
		categoryPanel.add(btnDelete, BorderLayout.CENTER);
		
		// define add button function
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addElement();
				
			}
		});

		// define delete button function
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if (rowIndex >= 0) {
					cat1.remove(rowIndex);
					if (Controller.saveAllCategory(cat1)) {
						deleteCategory(model,rowIndex);
					} else {
						JOptionPane.showMessageDialog(null,
								Utility.getPropertyValue(Constants.failure));
					}
				}else{
					String hint = Constants.categoryNoLineSelected;
					if(model.getRowCount() == 0){
						hint =Constants.categoryHintForEmptyInDelete;
					}
					JOptionPane.showMessageDialog(null,hint);				}
				
			}
		});
		// define edit button function
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editElement();
			}
		});
		btnAdd.setBounds(224, 149, 131, 23);
		add(categoryPanel, BorderLayout.NORTH);
		add(scrollPane);
		// get and display category list
		initialCategory(model);
	}
	
	/**
	 * function component for add button
	 */
private void addElement(){
	


	JTextField codeField = new JTextField();
	JTextField namefield = new JTextField();

	Object[] message = { Constants.categoryCode, codeField,
			Constants.categoryName, namefield };
	boolean isdone = true;
	int option;
	while (isdone) {
		option = JOptionPane.showConfirmDialog(null, message,
				Constants.addCategoryBtn,
				JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			String categoryCode = codeField.getText();
			String categoryName = namefield.getText();
			Category cat = new Category();
			cat.setCategoryCode(categoryCode);
			cat.setCategoryName(categoryName);
			String result = Controller.validateAndSaveCategory(cat);
			if (Constants.SUCCESS.equals(result)) {
				cat1.add(cat);
				addCategory(model,cat);
				isdone = false;
			} else {
				JOptionPane.showMessageDialog(null, result);
			}
		}else{
			isdone = false;
		}
	}
}
	/**
	 * function component for edit button
	 */

	private void editElement(){
		
		int rowIndex = table.getSelectedRow();
		Category cat = new Category();
		if (rowIndex >= 0) {
			Category categoriesItem =  cat1.get(rowIndex);

			JTextField codeField = new JTextField(categoriesItem.getCategoryCode());
			JTextField namefield = new JTextField(categoriesItem.getCategoryName());

			Object[] message = { Constants.categoryCode, codeField,
					Constants.categoryName, namefield };
			boolean isdone = true;
			int option;
			while (isdone) {
				option = JOptionPane.showConfirmDialog(null, message,
						Constants.addCategoryBtn,
						JOptionPane.OK_CANCEL_OPTION);
				
				String categoryCode = codeField.getText();
				String categoryName = namefield.getText();
				if (option == JOptionPane.OK_OPTION) {
					cat.setCategoryCode(categoryCode);
					cat.setCategoryName(categoryName);
					String result = Controller.validateAndSaveCategory(cat);
					if (Constants.SUCCESS.equals(result)) {
						cat1.remove(rowIndex);
						cat1.add(rowIndex, cat);
						isdone = false;
					} 
					else if(Constants.categoryExists.equals(result)){
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
					cat.setCategoryCode(categoryCode);
					cat.setCategoryName(categoryName);
				}
			}			
			
			if (Controller.saveAllCategory(cat1)) {
				editCategory(model,rowIndex,cat);
			} else {
				JOptionPane.showMessageDialog(null,
						Utility.getPropertyValue(Constants.failure));
			}
		}else{
			String hint = Constants.categoryNoLineSelected;
			if(model.getRowCount() == 0){
				hint =Constants.categoryHintForEmptyInEdit;
			}
			JOptionPane.showMessageDialog(null,hint);
		}
	}
	
	
	
	
	/**
	 * fresh Category table: may be use in future
	 */
	private void freshCategory(DefaultTableModel model) {
		// define Model for Table
	
		table.setModel(model);
		// align category field in center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);

		table.setModel(model);


		CategoryDao categoryDao = new CategoryDao();
		cat1 = categoryDao.retrieveAll();

		// Putting Values in the Category Table
		int i = 0;
		for (Category entry : cat1) {
		//	model.addRow(new Object[0]);
			model.setValueAt(entry.getCategoryCode(), i, 0);
			model.setValueAt(entry.getCategoryName(), i, 1);
			i++;
		}

	}
	
	
	/**
	 *  initial table of category
	 * @param model
	 */
	
	private void initialCategory(DefaultTableModel model) {
		// define Model for Table
		table.setModel(model);
	

		// align category field in center
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(String.class, centerRenderer);



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
	
	/**
	 * update view for add category
	 * @param model
	 * @param anCategory
	 */
	private void addCategory(DefaultTableModel model,Category anCategory) {
			table.setModel(model);
			int row = model.getRowCount();
			model.addRow(new Object[0]);
			model.setValueAt(anCategory.getCategoryCode(),row, 0);
			model.setValueAt(anCategory.getCategoryName(),row, 1);
	}
	
	/**
	 * update view for deleteCategory
	 * @param model
	 * @param row which row you want to delete
	 */
	private void deleteCategory(DefaultTableModel model,int row) {
		table.setModel(model);
		model.removeRow(row);
	}
	
	/**
	 * update view for edit category
	 * @param model
	 * @param row which row you want to edit
	 * @param anCategory an new category for replacing
	 */
	private void editCategory(DefaultTableModel model,int row,Category anCategory) {
		table.setModel(model);
		model.setValueAt(anCategory.getCategoryCode(), row, 0);
		model.setValueAt(anCategory.getCategoryName(), row, 1);
	}

}

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

import com.iss.storeApplication.business.CategoryService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.dao.CategoryDao;
import com.iss.storeApplication.domain.Category;


/**
 * @author luke
 */

public class CategoryView extends JPanel {

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

	private void initialCategory() {
		scrollPane = new JScrollPane();
		categoryPanel = new JPanel();
		// ScrollPane for Table
		scrollPane.setBounds(33, 41, 494, 90);
		// some setting on panel
		JButton btnGetRowSelected = new JButton(Constants.ADDCATEGORY_BTN);

		// Table
		table = new JTable();
		scrollPane.setViewportView(table);

		categoryPanel.add(btnGetRowSelected, BorderLayout.EAST);
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
					Category cat = new Category();
					cat.setCategoryCode(categoryCode);
					cat.setCategoryName(categoryName);
					CategoryService.validateAndSaveCategory(cat);
//					SwingUtility.refreshJpanel(categoryPanel);
//					SwingUtility.refreshJpanel(objectCat);
					freshCategory(model);
				}

			}
		});
		
		
		btnGetRowSelected.setBounds(224, 149, 131, 23);
		add(categoryPanel, BorderLayout.NORTH);
		add(scrollPane);





//get and display category list 
		freshCategory(model);
	}

	private void freshCategory(DefaultTableModel model) {
		//define Model for Table
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
		
		//align category field in center 
		 DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
         centerRenderer.setHorizontalAlignment( JLabel.CENTER );
         table.setDefaultRenderer(String.class, centerRenderer); 

		table.setModel(model);

		model.addColumn(Constants.CATEGORYID_LABEL);
		model.addColumn(Constants.CATEGORYNAME_LABEL);
		List<Category> cat1;
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

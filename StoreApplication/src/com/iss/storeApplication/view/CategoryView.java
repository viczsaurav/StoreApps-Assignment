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

import com.iss.storeApplication.dao.CommonDao;
import com.iss.storeApplication.domain.Category;

public class CategoryView extends JPanel {

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	
	
	public CategoryView() {
		setBounds(100, 100, 580, 242);
		
		setLayout(new BorderLayout());

		jPanelExtract();
		


	}

	private void jPanelExtract() {
		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		// Get Row Selected
		JPanel north = new JPanel(new BorderLayout());
		JButton btnGetRowSelected = new JButton("Add");
		north.add(btnGetRowSelected,BorderLayout.EAST);
		btnGetRowSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//						JOptionPane. showInputDialog(null, "hello");
						
						JTextField field1 = new JTextField();  
						JTextField field2 = new JTextField();  
 
						Object[] message = {  
						    "CategoryId:", field1,  
						    "CategoryName:", field2,  
 
						};  
						int option = JOptionPane.showConfirmDialog(null, message, "Add category", JOptionPane.OK_CANCEL_OPTION);  
						if (option == JOptionPane.OK_OPTION)  
						{  
						    String categoryCode = field1.getText();  
						    String categoryName = field2.getText();  
						    System.out.println("categoryCode:"+categoryCode);
						    System.out.println("categoryCode:"+categoryName);
						} 

			}

		});
		btnGetRowSelected.setBounds(224, 149, 131, 23);
		add(north,BorderLayout.NORTH);
		add(scrollPane);
		// Table
		final JTable table = new JTable();
		scrollPane.setViewportView(table);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {

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
		
		
		CommonDao d1=new CommonDao();
		String[] strc1= null;
		List<Category> cat1=new ArrayList<Category>();
		try {
			cat1 = d1.retrieveAllRecordArray("Category.dat",Category.class);
			table.setModel(model);

			model.addColumn("CategoryID");
			model.addColumn("Name");

			// Data Row
			int i = 0 ;
			for (Category entry:cat1) {
				model.addRow(new Object[0]);
				model.setValueAt(entry.getCategoryCode(), i, 0);
				model.setValueAt(entry.getCategoryName(), i, 1);
				i++;
			}
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
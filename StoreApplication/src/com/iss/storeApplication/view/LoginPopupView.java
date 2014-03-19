package com.iss.storeApplication.view;

import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.dao.CommonDao;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.StoreKeeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;




public class LoginPopupView {
	
	JButton login,cancel;
	static JTextField txtUname;
	static JPasswordField txtPassword;	
	static String[] ConnectOptionNames = { "Login", "Cancel" };
	int result;
	

	public static boolean showLoginDialog(MainView mainView)
	{
		JPanel loginPanel=new JPanel();
		txtUname = new JTextField();
		txtPassword = new JPasswordField();   
        loginPanel.setLayout(new GridLayout(2, 3));
        loginPanel.setBackground( new Color(255, 0, 0, 20) );        
        loginPanel.setOpaque(false); 
        loginPanel.add(new JLabel("User Name:"));
        loginPanel.add(txtUname);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(txtPassword);  
		int result =  JOptionPane.showOptionDialog(mainView, loginPanel, 
			   Constansts.LOGIN_DIALOG_TITLE,
                JOptionPane.OK_CANCEL_OPTION, 
                JOptionPane.INFORMATION_MESSAGE,
                null, ConnectOptionNames, 
                ConnectOptionNames[0]);
		if (result == JOptionPane.OK_OPTION) {	
           String userName= txtUname.getText();
           char[] password =txtPassword.getPassword();
          
           CommonDao commonRetrieve=new CommonDao();
			//String[] strc1= null;
			List<StoreKeeper> storeKeeperList=new ArrayList<StoreKeeper>();
			boolean isLoginSuccess = false;
			try {
				storeKeeperList = commonRetrieve.retrieveAllRecordArray("StoreKeepers.dat", StoreKeeper.class);
				HashMap<String, StoreKeeper> userNamestoreKeeperMap=new HashMap<String, StoreKeeper>();
				for(StoreKeeper s:storeKeeperList)
				{
					userNamestoreKeeperMap.put(s.getUserName(), s);
				}
				Set mapSet = (Set) userNamestoreKeeperMap.entrySet();
                //Create iterator on Set 
                Iterator mapIterator = mapSet.iterator();
                while (mapIterator.hasNext()) {
                    Map.Entry mapEntry = (Map.Entry) mapIterator.next();
                    // getKey Method of HashMap access a key of map
                    String keyValue = (String) mapEntry.getKey();
                    //getValue method returns corresponding key's value
                    StoreKeeper value = (StoreKeeper) mapEntry.getValue();
                    char[] passwordValue=value.getPassword().toCharArray();
                                        
                    if (Arrays.equals(password, passwordValue) && userName.equals(keyValue))
                    {   
                    	JOptionPane.showMessageDialog(null,"Login successful!","Message",JOptionPane.INFORMATION_MESSAGE);
                    	isLoginSuccess = true;
                        break;
                    }
                                        
            }
                if (!isLoginSuccess)
                {
                	JOptionPane.showMessageDialog(null,"Invalid username and password","Message",JOptionPane.ERROR_MESSAGE); 
                    txtUname.setText("");
                    txtPassword.setText("");  
                    showLoginDialog(mainView);
                    
                } 
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
           
			return true;		
			
              
        } else {
        	mainView.dispatchEvent(new WindowEvent(mainView, WindowEvent.WINDOW_CLOSING));
            return false;        
	}

	}
	
	

}

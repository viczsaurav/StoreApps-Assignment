package com.iss.storeApplication.business;

import java.util.Map;

import javax.swing.JOptionPane;

import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.dao.StoreKeeperDao;
import com.iss.storeApplication.domain.StoreKeeper;
import com.iss.storeApplication.view.MainView;

/**
 * 
 * @author sakthi
 * 
 */
public class LoginService {

	public static boolean validateUser(StoreKeeper storekeeper) {
		MainView main = new MainView();

		if (storekeeper.getUserName().equals("") && storekeeper.getPassword().equals("")) {
			JOptionPane.showMessageDialog(null,
					Constansts.LOGIN_WITHOUT_USERNAME_AND_PASSWORD, "Message",
					JOptionPane.ERROR_MESSAGE);
			main.showLoginView();
			return false;
		}

		else if (storekeeper.getUserName().equals("")) {
			JOptionPane.showMessageDialog(null,
					Constansts.LOGIN_WITHOUT_USERNAME, "Message",
					JOptionPane.ERROR_MESSAGE);
			main.showLoginView();
			return false;
		} else if (storekeeper.getPassword().equals("")) {
			JOptionPane.showMessageDialog(null,
					Constansts.LOGIN_WITHOUT_PASSWORD, "Message",
					JOptionPane.ERROR_MESSAGE);
			main.showLoginView();
			return false;
		} else {
			// Map<String, StoreKeeper> storeKeeperMap=StoreKeeperDao.getStoreKeeperMap();
			Map<String, String> storeKeeperMap = StoreKeeperDao
					.getStoreKeeperMap();
			if (storeKeeperMap.containsValue(storekeeper.getUserName()) && storeKeeperMap.containsValue(storekeeper.getPassword())) {
				JOptionPane.showMessageDialog(null,
						Constansts.LOGIN_SUCCESS_MESSAGE, "Message",
						JOptionPane.ERROR_MESSAGE);
				return true;
			} else {
				JOptionPane.showMessageDialog(null,
						Constansts.LOGIN_FAILURE_MESSAGE, "Message",
						JOptionPane.ERROR_MESSAGE);
				main.showLoginView();
				return false;
			}

		}

	}
}

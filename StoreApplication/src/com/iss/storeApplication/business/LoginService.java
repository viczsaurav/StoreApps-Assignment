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

	/**
	 * 
	 * @param storekeeper
	 * @return
	 * 
	 *         It validates store keeper name and store keeper password
	 */
	public static String validateUser(StoreKeeper storekeeper) {

		if (storekeeper.getUserName().equals("")
				&& storekeeper.getPassword().equals("")) {
			return Constansts.MSG_USR_PWD_NULL;
		} else if (storekeeper.getUserName().equals("")) {
			return Constansts.MSG_USR_NULL;
		} else if (storekeeper.getPassword().equals("")) {
			return Constansts.MSG_PWD_NULL;
		} else {
			// Map<String, StoreKeeper>
			// storeKeeperMap=StoreKeeperDao.getStoreKeeperMap();
			Map<String, String> storeKeeperMap = StoreKeeperDao
					.getStoreKeeperMap();
			if (storeKeeperMap.containsValue(storekeeper.getUserName())
					&& storeKeeperMap.containsValue(storekeeper.getPassword())) {

				return Constansts.LOGIN_SUCCESS_MESSAGE;
			}
			else if(storeKeeperMap.containsValue(storekeeper.getUserName()))
			{
				return Constansts.LOGIN_CORRECT_PASSWORD;
			}
			else if(storeKeeperMap.containsValue(storekeeper.getPassword()))
			{				
				return Constansts.LOGIN_CORRECT_USERNAME;
			}
			else {
				return Constansts.MSG_INVALID_USR_PWD;
			}

		}

	}
}

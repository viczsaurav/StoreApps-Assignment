package com.iss.storeApplication.business;

import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.dao.StoreKeeperDao;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */
public class LoginService {

	private static StoreKeeperDao storekeeperDao = new StoreKeeperDao();

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
			return Constants.MSG_USR_PWD_NULL;
		} else if (storekeeper.getUserName().equals("")) {
			return Constants.MSG_USR_NULL;
		} else if (storekeeper.getPassword().equals("")) {
			return Constants.MSG_PWD_NULL;
		} else {

			Map<String, StoreKeeper> storeKeeperMap = storekeeperDao.getMap();
			
			if (storeKeeperMap.containsKey(storekeeper.getUserName().trim())) {
				StoreKeeper sk = storeKeeperMap.get(storekeeper.getUserName());
				if (sk.equals(storekeeper))
					return Constants.LOGIN_SUCCESS_MESSAGE;
				else
					return Constants.LOGIN_INVALID_PASSWORD;
			} else {
				return Constants.MSG_INVALID_USR;
			}

		}

	}
}

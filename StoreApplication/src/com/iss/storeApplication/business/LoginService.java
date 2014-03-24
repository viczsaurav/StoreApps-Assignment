package com.iss.storeApplication.business;

import java.util.Map;

import com.iss.storeApplication.common.Constansts;
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
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static String validateUser(StoreKeeper storekeeper)
			throws InstantiationException, IllegalAccessException {

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
			Map<String, StoreKeeper> storeKeeperMap = storekeeperDao
					.getStoreKeeperMap();
			if (storeKeeperMap.containsKey(storekeeper.getUserName().trim())) {
				StoreKeeper sk = storeKeeperMap.get(storekeeper.getUserName());
				if (sk.equals(storekeeper))
					return Constansts.LOGIN_SUCCESS_MESSAGE;
				else
					return Constansts.LOGIN_INVALID_PASSWORD;
			}
			/*
			 * else if(storeKeeperMap.containsValue(storekeeper.getUserName()))
			 * { return Constansts.LOGIN_CORRECT_PASSWORD; }
			 */
			/*
			 * else if(storeKeeperMap.containsValue(storekeeper.getPassword()))
			 * { return Constansts.LOGIN_CORRECT_USERNAME; }
			 */
			else {
				return Constansts.MSG_INVALID_USR;
			}

		}

	}
}

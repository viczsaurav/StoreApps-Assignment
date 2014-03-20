package com.iss.storeApplication.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */
public class StoreKeeperDao {

	//public static  Map<String,StoreKeeper> getStoreKeeperMap()
	public static  Map<String,String> getStoreKeeperMap()
	{
		/*HashMap<String, StoreKeeper> userNamestoreKeeperMap= null;
		CommonDao commonRetrieve = new CommonDao();			 
		List<StoreKeeper> storeKeeperList = new ArrayList<StoreKeeper>();
		boolean isLoginSuccess = false;
		try {
		
			storeKeeperList = commonRetrieve.retrieveAllRecordArray("StoreKeepers.dat", StoreKeeper.class);
			userNamestoreKeeperMap = new HashMap<String, StoreKeeper>();
			for (StoreKeeper s : storeKeeperList) {
				userNamestoreKeeperMap.put(s.getUserName(), s);
			}
		}
		catch(Exception e)
		{
			
		}*/
		
		HashMap<String, String> userNamestoreKeeperMap = new HashMap<String, String>();

		userNamestoreKeeperMap.put("1", "One");
		userNamestoreKeeperMap.put("2", "Two");
		userNamestoreKeeperMap.put("3", "Three");

	    String key = "3";
			
		return userNamestoreKeeperMap;
	}
}

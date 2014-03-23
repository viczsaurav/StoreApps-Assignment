package com.iss.storeApplication.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */
public class StoreKeeperDao {
	
	private static  String fileName=Constansts.FILENAME_STOREKEEPER+Constansts.FILE_EXT_SEPERATOR+Constansts.FILE_EXTENSION;
	//public static  Map<String,StoreKeeper> getStoreKeeperMap()
	public static  Map<String,StoreKeeper> getStoreKeeperMap() throws InstantiationException, IllegalAccessException
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
		List<StoreKeeper> storekeepers=CommonDao.retrieveAllRecordArray(fileName, StoreKeeper.class);
		HashMap<String, StoreKeeper> userNamestoreKeeperMap = new HashMap<String, StoreKeeper>();
		
		for(StoreKeeper s:storekeepers)
		{
			userNamestoreKeeperMap.put(s.getUserName(), s);
		}
		/*userNamestoreKeeperMap.put("1", "One");
		userNamestoreKeeperMap.put("2", "Two");
		userNamestoreKeeperMap.put("3", "Three");

	    String key = "3";*/
			
		return userNamestoreKeeperMap;
	}
}

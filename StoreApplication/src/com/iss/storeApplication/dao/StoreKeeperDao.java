package com.iss.storeApplication.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */
public class StoreKeeperDao implements CommonDao<StoreKeeper> {

	private String fileName = Constants.FILENAME_STOREKEEPER
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;



	/**
	 * Save Storekeeper to file
	 */
	@Override
	public boolean save(StoreKeeper s,boolean append) 
	{

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));

			out.println(s.getCommaSeperatedValue());
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}
	}

	/**
	 * Retrieve All Storekeepers
	 */
	@Override
	public List<StoreKeeper> retrieveAll() {
		List<StoreKeeper> storeKeepers = new ArrayList<StoreKeeper>();

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String row;
			while ((row = br.readLine()) != null) {
				String[] rowValues = row.split(",");
				StoreKeeper s = new StoreKeeper();
				s.setUserName(rowValues[0]);
				s.setPassword(rowValues[1]);
				storeKeepers.add(s);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}

		return storeKeepers;
	}

	
	/**
	 * get Storekeeper Map used to search storekeeper from list of record.
	 * 
	 * @return
	 */
	@Override
	public Map<String, StoreKeeper> getMap() {
		// TODO Auto-generated method stub
		List<StoreKeeper> storekeepers = retrieveAll();
		HashMap<String, StoreKeeper> userNamestoreKeeperMap = new HashMap<String, StoreKeeper>();

		for (StoreKeeper s : storekeepers) {
			userNamestoreKeeperMap.put(s.getUserName(), s);
		}

		return userNamestoreKeeperMap;
	}


}

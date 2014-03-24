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

import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */
public class StoreKeeperDao implements CommonDao<StoreKeeper> {

	private String fileName = Constansts.FILENAME_STOREKEEPER
			+ Constansts.FILE_EXT_SEPERATOR + Constansts.FILE_EXTENSION;

	/**
	 * get Storekeeper Map used to search storekeeper from list of record.
	 * 
	 * @return
	 */
	public Map<String, StoreKeeper> getStoreKeeperMap() {

		List<StoreKeeper> storekeepers = retrieveAll();
		HashMap<String, StoreKeeper> userNamestoreKeeperMap = new HashMap<String, StoreKeeper>();

		for (StoreKeeper s : storekeepers) {
			userNamestoreKeeperMap.put(s.getUserName(), s);
		}

		return userNamestoreKeeperMap;
	}

	/**
	 * Save Storekeeper to file
	 */
	@Override
	public void save(StoreKeeper s) {

		try {
			File file = new File(Constansts.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));

			out.println(s.getCommaSeperatedValue());
			out.close();
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
		}
	}

	/**
	 * Retrieve All Storekeepers
	 */
	@Override
	public List<StoreKeeper> retrieveAll() {
		List<StoreKeeper> storeKeepers = new ArrayList<StoreKeeper>();

		try {
			File file = new File(Constansts.DATA_FILE_DIR, fileName);
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
}

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
import com.iss.storeApplication.domain.Vendor;

public class VendorDao implements CommonDao<Vendor>{
	private String fileName = null;



	/**
	 * Save Vendor to file
	 */
	public void save(Vendor v) {

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));

			out.println(v.getCommaSeperatedValue());
			out.close();
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
		}
	}

	/**
	 * Retrieve All Storekeepers
	 */
	@Override
	public List<Vendor> retrieveAll() {
		List<Vendor> Vendors = new ArrayList<Vendor>();

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String row;
			while ((row = br.readLine()) != null) {
				String[] rowValues = row.split(",");
				Vendor v = new Vendor();
				v.setName(rowValues[0]);
				v.setDescription(rowValues[1]);
				
				Vendors.add(v);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}

		return Vendors;
	}

	
	/**
	 * get Storekeeper Map used to search storekeeper from list of record.
	 * 
	 * @return
	 */
	@Override
	public Map<String, Vendor> getMap() {
		// TODO Auto-generated method stub
		List<Vendor> vendors = retrieveAll();
		HashMap<String, Vendor> vendorsMap = new HashMap<String, Vendor>();

		for (Vendor v : vendors) {
			vendorsMap.put(v.getName(), v);
		}

		return vendorsMap;
	}
}

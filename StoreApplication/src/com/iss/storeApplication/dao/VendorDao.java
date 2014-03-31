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
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.domain.Vendor;

public class VendorDao implements CommonDao<Vendor> {

	private String fileName = null;

	/**
	 * Save Vendor to file
	 */
	public boolean save(Vendor v, boolean append) {

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));

			out.println(v.getCommaSeperatedValue());
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}
	}
	
	public boolean save(Vendor v,String categoryCode,boolean append)
	{
		fileName=Constants.FILENAME_VENDORS+categoryCode+Constants.FILE_EXTENSION;
		return save(v, append);
	}
	
	public List<Vendor> retrieveAll(String categoryCode)
	{
		fileName=Constants.FILENAME_VENDORS+categoryCode+Constants.FILE_EXTENSION;
		return retrieveAll();
	}
	
	public Vendor getFirstVendor(String categoryCode)
	{
		List<Vendor> vendors=retrieveAll(categoryCode);
		if(vendors.size()>0)
			return vendors.get(0);
		else
			return null;
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
				if (!StringUtility.isEmpty(row)) {
					String[] rowValues = row.split(",");
					Vendor v = new Vendor();
					v.setName(rowValues[0]);
					v.setDescription(rowValues[1]);

					Vendors.add(v);
				}
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

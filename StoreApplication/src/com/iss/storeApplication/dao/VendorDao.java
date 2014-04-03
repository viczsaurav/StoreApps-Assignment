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
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Vendor;

public class VendorDao implements CommonDao<Vendor> {

	private String fileName = null;
	private CategoryDao categoryDao = new CategoryDao();

	/**
	 * Save Vendor to file
	 */
	public boolean save(Vendor v,Category c, boolean append) {
		
		fileName = getFileName(c.getCategoryCode());
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

	

	public Vendor getFirstVendor(String categoryCode) {
		List<Vendor> vendors = retriveVendors(categoryCode);
		if (vendors.size() > 0)
			return vendors.get(0);
		else
			return null;
	}

	/**
	 * Retrieve All Vendors
	 */

	public List<Vendor> retrieveAll() {
		List<Vendor> vendors = new ArrayList<Vendor>();

		for (Category c : categoryDao.retrieveAll()) {
			List<Vendor> vendorCategory = retriveVendors(c.getCategoryCode());
			for (Vendor v : vendorCategory) {
				if (vendors.contains(v))// same vendor already added so dont add
										// vendor. add category
				{
					Vendor v1 = vendors.get(vendors.indexOf(v));
					v1.getCategories().add(c);
				} else {
					vendors.add(v);
				}
			}
		}

		return vendors;
	}

	public List<Vendor> retriveVendors(String categoryCode) {
		fileName = getFileName(categoryCode);
		List<Vendor> vendors = new ArrayList<Vendor>();
		Category c = categoryDao.get(categoryCode);
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
					List<Category> categories = new ArrayList<Category>();
					categories.add(c);
					v.setCategories(categories);
					vendors.add(v);
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}

		return vendors;
	}

	private String getFileName(String categoryCode) {
		return Constants.FILENAME_VENDORS + categoryCode
				+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;
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

	public boolean save(Vendor v, boolean append) {
		List<Category> categories=v.getCategories();
		for (Category c : categories) {
			if (!save(v, c, true))
				return false;
		}
		return true;
	}
	
	public boolean delete(Vendor vendor)
	{
		List<Vendor> vendors=retrieveAll();
		if(vendors.contains(vendor))
		{
			vendors.remove(vendor);
		}
		
		return saveAll(vendors);
		
	}
	
	/*
	 * Clears file and saves list of discount object to file.
	 */
	public boolean saveAll(List<Vendor> vendors) {
		// if (discounts.size() == 0) {
		
		
		List<Category> categories=categoryDao.retrieveAll();
		for(Category c:categories)
		{
			fileName = getFileName(c.getCategoryCode());
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!Utility.clearFile(file))
				return false;
		}
		

		// }
		for (Vendor v : vendors) {
			
			if (!save(v, true)) {
				return false;
			}
		}
		return true;
	}

}

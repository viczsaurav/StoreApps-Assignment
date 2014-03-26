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
import com.iss.storeApplication.domain.Category;

public class CategoryDao implements CommonDao<Category> {

	private String fileName = Constants.FILENAME_CATEGORY
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	/**
	 * Save category to file
	 */
	@Override
	public boolean save(Category c,boolean append) {
		// TODO Auto-generated method stub

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));
			out.println(c.getCommaSeperatedValue());
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}

	}
	
	/**
	 * List of all categories
	 */

	@Override
	public List<Category> retrieveAll() {
		// TODO Auto-generated method stub
		List<Category> Categories = new ArrayList<Category>();

		try {

			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String row;
			while ((row = br.readLine()) != null) {
				String[] rowValues = row.split(",");
				Category c = new Category();
				c.setCategoryCode(rowValues[0]);
				c.setCategoryName(rowValues[1]);
				Categories.add(c);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}
		return Categories;
	}
	

	/**
	 * Used to search for a category in map of categories
	 */

	public Category get(String searchKey) {
		// TODO Auto-generated method stub
		Map<String, Category> categoriesMap = getMap();
		return categoriesMap.get(searchKey);

	}
	
	/**
	 * Used to return a Map of categories with category name as key and category object as value
	 */

	@Override
	public Map<String, Category> getMap() {
		// TODO Auto-generated method stub

			List<Category> categories = retrieveAll();
			HashMap<String, Category> categoriesMap = new HashMap<String, Category>();

			for (Category c : categories) {
				categoriesMap.put(c.getCategoryCode(),c);
			}

			return categoriesMap;
		}
	}

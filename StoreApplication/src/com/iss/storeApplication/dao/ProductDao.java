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
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.StoreKeeper;

public class ProductDao implements CommonDao<Product> {

	private String fileName = Constants.PRODUCT_FILE_NAME
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	/**
	 * Save the product to file
	 */
	@Override
	public boolean save(Product t,boolean append) {
		// TODO Auto-generated method stub

		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, true)));
			out.println(t.getCommaSeperatedValue());
			out.close();
			return true;
		} catch (IOException e) {
			System.out.println("IOException :" + e.getMessage());
			return false;
		}

	}

	@Override
	public List<Product> retrieveAll() {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<Product>();
		try {

			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String row;
			while ((row = br.readLine()) != null) {
				String[] rowValues = row.split(",");
				Product t = new Product();
				t.setProductId(rowValues[0]);
				t.setProductName(rowValues[1]);
				t.setDescription(rowValues[2]);
				t.setQtyAvailable(Integer.parseInt(rowValues[3]));
				t.setPrice(Double.parseDouble(rowValues[4]));
				t.setBarCode(Long.parseLong(rowValues[5]));
				t.setReorderQty(Integer.parseInt(rowValues[6]));
				t.setOrderQty(Integer.parseInt(rowValues[7]));
				products.add(t);
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + fileName);

		} catch (IOException e) {
			System.out.println("IOException when creating file :" + fileName
					+ e.getMessage());

		}
		return products;
	}

	
	public Product get(String searchKey) {
		// TODO Auto-generated method stub
		Map<String, Product> productsMap = getMap();
		return productsMap.get(searchKey);

	}

	@Override
	public Map<String, Product> getMap() {
		// TODO Auto-generated method stub

			List<Product> Products = retrieveAll();
			HashMap<String, Product> productsMap = new HashMap<String, Product>();

			for (Product p : Products) {
				productsMap.put(p.getProductId(),p);
			}
			return productsMap;
		}
	}


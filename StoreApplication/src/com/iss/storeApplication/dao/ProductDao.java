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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Product;

public class ProductDao implements CommonDao<Product> {

	private CategoryDao category = new CategoryDao();
	private String fileName = Constants.PRODUCT_FILE_NAME
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	/**
	 * Save the product to file
	 */

	public boolean save(Product t, boolean append) {
		try {
			File file = new File(Constants.DATA_FILE_DIR, fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file, append)));
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
				if (!StringUtility.isEmpty(row)) {
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
					t.setCategory(category.get(t.getProductId().split("/")[0]
							.trim()));
					products.add(t);
				}
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
			productsMap.put(p.getProductId(), p);
		}
		return productsMap;
	}

	public Map<Long, Product> getBarCodeProductMap() {
		List<Product> Products = retrieveAll();
		HashMap<Long, Product> barcodeProductsMap = new HashMap<Long, Product>();
		for (Product p : Products) {
			barcodeProductsMap.put(p.getBarCode(), p);
		}
		return barcodeProductsMap;
	}

	public boolean saveAll(List<Product> listProducts) {
		File file = new File(Constants.DATA_FILE_DIR, fileName);
		if (!Utility.clearFile(file))
			return false;
		for (Product p : listProducts) {
			if (!save(p, true)) {
				return false;
			}
		}
		return true;
	}

	public List<Product> getProductsBelowThreshold(Category c) {

		if (c == null) {
			return new ArrayList<Product>();
		}
		List<Product> products = retrieveAll();
		for (Iterator<Product> iterator = products.iterator(); iterator
				.hasNext();) {
			Product p = iterator.next();

			if (!p.getCategory().getCategoryCode().trim()
					.equalsIgnoreCase(c.getCategoryCode().trim())) {
				iterator.remove();
			} else if (p.getQtyAvailable() > p.getReorderQty()) // above
																// threshold
			{
				iterator.remove();
			}
		}
		return products;

	}

	public boolean editProduct(Product p) {
		Map<String, Product> map = getMap();
		if (map.containsKey(p.getProductId())) {
			map.put(p.getProductId(), p);
			List<Product> products = new ArrayList<>(map.values());
			if (!saveAll(products)) {
				return false;
			}
		} else {
			return false;
		}
		return true;

	}

}
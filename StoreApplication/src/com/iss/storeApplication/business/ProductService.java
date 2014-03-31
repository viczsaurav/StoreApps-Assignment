package com.iss.storeApplication.business;

/**
 * @Author Saurav
 * 
 */

import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.dao.ProductDao;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.SeasonalDiscount;

public class ProductService {

	static ProductDao productDao = new ProductDao();

	/**
	 * It validates category against null and duplicates. Saves the new Product
	 * 
	 * @param producr
	 * @return
	 */

	public static String validateAndSaveProduct(Product p) {
		String msg = validateProduct(p);
		if (msg.equals(Constants.SUCCESS)) {
			p.setProductId(generateProductID(p.getCategory()));
			if (productDao.save(p, true))
				return Constants.SUCCESS;
			else
				return Utility.getPropertyValue(Constants.failure);
		} else {
			return msg;
		}
	}
	
	//Generate New Product ID
	private static String generateProductID(Category category) {
		int catProdCnt = 0;
		List<Product> products = productDao.retrieveAll();
		for (Product p : products) {
			if (p.getCategory().getCategoryCode()
					.equals(category.getCategoryCode())) {
				catProdCnt++;
			}
		}
		return category.getCategoryCode() + "/" + (catProdCnt + 1);
	}

	public static Product getProduct(Long barcode) {
		Map<Long, Product> barcodeProductMap = productDao
				.getBarCodeProductMap();
		if (barcodeProductMap.containsKey(barcode))
			return barcodeProductMap.get(barcode);
		else
			return null;
	}

	/**
	 * Clears and Saves Product objects to file
	 * 
	 */
	public static boolean saveAll(List<Product> listProducts) {
		return productDao.saveAll(listProducts);
	}

	public static String validateProduct(Product p) {
		if (p == null) {
			return Utility.getPropertyValue(Constants.validateEmptyMessage);
		}
		if (	p.getCategory() == null 	|| 
				p.getProductName() == null	|| 
				p.getDescription() == null 	|| 
				p.getOrderQty() == null		|| 
				p.getPrice() == null 		|| 
				p.getOrderQty() == null		|| 
				p.getReorderQty() == null) 	{
			return Constants.ALL_FIELDS_REQUIRED;
		}

		return Constants.SUCCESS;
	}

	public static List<Product> getProducts() {
		return productDao.retrieveAll();
	}

	public static List<Product> getProductsBelowThreshold(Category c) {
		return productDao.getProductsBelowThreshold(c);
	}

	public static boolean editProduct(Product p) {

		return productDao.editProduct(p);
	}
}

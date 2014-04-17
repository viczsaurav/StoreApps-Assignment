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
import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

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

	// Generate New Product ID
	public static String generateProductID(Category category) {
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
		if (p.getCategory() == null
				|| StringUtility.isEmpty(p.getProductName())
				|| StringUtility.isEmpty(p.getDescription())
				|| StringUtility.isEmpty(Integer.toString(p.getOrderQty()))
				|| StringUtility.isEmpty(Double.toString(p.getPrice()))
				|| StringUtility.isEmpty(Long.toString(p.getBarCode()))
				|| StringUtility.isEmpty(Integer.toString(p.getOrderQty()))
				|| StringUtility.isEmpty(Integer.toString(p.getReorderQty()))) {
			return Constants.ALL_FIELDS_REQUIRED;
		}
		if (p.getQtyAvailable() < 0 || p.getOrderQty() < 0
				|| p.getReorderQty() < 0) {
			return Utility.getPropertyValue(Constants.msgnotNegative);
		}
		if (!(p.getPrice() > 0)) {
			return Utility.getPropertyValue(Constants.pricemorethanzero);
		}

		return Constants.SUCCESS;
	}

	public static List<Product> getProducts() {
		return productDao.retrieveAll();
	}

	public static Map<Long, Product> getProductMap() {
		return productDao.getBarCodeProductMap();
	}

	public static List<Product> getProductsBelowThreshold(Category c) {
		return productDao.getProductsBelowThreshold(c);
	}

	public static boolean editProduct(Product p) {
		return productDao.editProduct(p);
	}
}

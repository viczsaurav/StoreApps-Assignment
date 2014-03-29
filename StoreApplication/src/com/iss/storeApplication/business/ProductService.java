package com.iss.storeApplication.business;

/**
 * @Author Saurav
 * 
 */

import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.dao.ProductDao;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Product;

public class ProductService {

	static ProductDao productDao = new ProductDao();

	/**
	 * It validates category against null and duplicates.
	 * 
	 * @param c
	 * @return
	 */

	public static String validateAndSaveProduct(Product p) {
		
		boolean isEmpty = false;
		if (p.getCategory() != null) {
			generateProductID(p.getCategory());
		}
		
		if( p.getProductId() == null 	||
			p.getProductName() == null 	||
			p.getDescription() == null	||
			p.getPrice() == null 		||
			p.getOrderQty() == null 	||
			p.getReorderQty() == null)  {
			isEmpty = true;
		}
		if (isEmpty){
			return Constants.ALL_FIELDS_REQUIRED;
		}
		return Constants.SUCCESS;
	}

	private static void generateProductID(Category category) {
		// TODO Auto-generated method stub
		
	}
	
	public static Product getProduct(Long barcode)
	{
		Map<Long,Product> barcodeProductMap=productDao.getBarCodeProductMap();
		if(barcodeProductMap.containsKey(barcode))
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
}

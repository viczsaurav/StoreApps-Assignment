package com.iss.storeApplication.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iss.storeApplication.business.ProductService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.controller.Controller;
import com.iss.storeApplication.dao.CategoryDao;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Product;

public class ProductServiceTest {

	public static List<Product> products = new ArrayList<Product>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Product newProduct1 = new Product();
		Product newProduct2 = new Product();
		Product newProduct3 = new Product();

		// Test 1 = None of the values can be empty
		newProduct1.setProductId("CLO/1");
		newProduct1.setProductName("");
		newProduct1.setDescription("Nice Jacket");
		newProduct1.setQtyAvailable(Integer.parseInt("10"));
		newProduct1.setPrice(Double.parseDouble("15"));
		newProduct1.setBarCode(Long.parseLong("1345"));
		newProduct1.setReorderQty(Integer.parseInt("10"));
		newProduct1.setOrderQty(Integer.parseInt("100"));
		newProduct1.setCategory(Controller.getCategory("CLO"));


		// Test 2 = Price should be greater than zero
		newProduct2.setProductId("MUG/1");
		newProduct2.setProductName("NUS Mug");
		newProduct2.setDescription("Nice Mug");
		newProduct2.setQtyAvailable(Integer.parseInt("15"));
		newProduct2.setPrice(Double.parseDouble("0"));
		newProduct2.setBarCode(Long.parseLong("1345"));
		newProduct2.setReorderQty(Integer.parseInt("10"));
		newProduct2.setOrderQty(Integer.parseInt("100"));
		newProduct2.setCategory(Controller.getCategory("MUG"));

		// Test 3 = Non negative values
		newProduct3.setProductId("STA/1");
		newProduct3.setProductName("NUS Pen");
		newProduct3.setDescription("Nice Pen");
		newProduct3.setQtyAvailable(Integer.parseInt("-15"));
		newProduct3.setPrice(Double.parseDouble("10"));
		newProduct3.setBarCode(Long.parseLong("1345"));
		newProduct3.setReorderQty(Integer.parseInt("-10"));
		newProduct3.setOrderQty(Integer.parseInt("100"));
		newProduct3.setCategory(Controller.getCategory("STA"));
		
		products.add(newProduct1);
		products.add(newProduct2); 
		products.add(newProduct3);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		products.clear();
	}

	@Test
	public void testValidateProduct() {

		// For Test 1 = None of the values can be empty
		for (Product p : products) {
			System.out.println("\n"+ p.getProductId());			
		}

		assertEquals(ProductService.validateProduct(products.get(0)),
				Constants.ALL_FIELDS_REQUIRED);

		// For Test 2 = Price should be greater than zero
		assertEquals(ProductService.validateProduct(products.get(1)),
				Utility.getPropertyValue(Constants.pricemorethanzero));

		// For Test 3 = Non negative values
		assertEquals(ProductService.validateProduct(products.get(2)),
				Utility.getPropertyValue(Constants.msgnotNegative));
	}

	@Test
	public void testGenerateProductID() {
		Category category = (new CategoryDao()).get("MUG");
		String newPID = ProductService.generateProductID(category);
		assertEquals("MUG/4", newPID);
	}
}

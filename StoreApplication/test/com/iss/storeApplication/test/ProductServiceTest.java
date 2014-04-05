package com.iss.storeApplication.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iss.storeApplication.domain.Product;

public class ProductServiceTest {
	
	public static List<Product> products = new ArrayList<Product>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		products.clear();
	}

	@Test
	public void testValidateProduct() {
		fail("Not yet implemented");
	}

	

}

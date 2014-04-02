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
	public void testValidateAndSaveProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateProduct() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProducts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProductsBelowThreshold() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditProduct() {
		fail("Not yet implemented");
	}

}

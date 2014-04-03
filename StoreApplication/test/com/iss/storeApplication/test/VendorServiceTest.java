package com.iss.storeApplication.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iss.storeApplication.business.VendorService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Vendor;

/**
 * 
 * @author milan
 *
 */
public class VendorServiceTest {

	private static List<Vendor> vendors = new ArrayList<Vendor>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		Category c = new Category();
		c.setCategoryCode("CLO");
		c.setCategoryName("Cloth");
		List <Category> categories=new ArrayList<Category>();
		categories.add(c);
		
		// 1. null test
		Vendor v = new Vendor();
		vendors.add(v);

		// 2. vendor name null test
		v = new Vendor();
		v.setDescription("desc");
		v.setCategories(categories);
		vendors.add(v);

		// 3. vendor desc null test
		v = new Vendor();
		v.setName("Milan");
		v.setCategories(categories);
		vendors.add(v);

		// 4 category null test
		v = new Vendor();
		v.setName("Milan");
		v.setDescription("desc");
		vendors.add(v);

		// 5 valid data
		v = new Vendor();
		v.setName("Milan");
		v.setDescription("desc");
		v.setCategories(categories);
		vendors.add(v);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		vendors.clear();
	}

	@Test
	public void test() {
		assertEquals(VendorService.validateVendor(vendors.get(0)),
				Constants.validateEmptyMessage);
		assertEquals(VendorService.validateVendor(vendors.get(1)),
				Constants.validateEmptyMessage);
		assertEquals(VendorService.validateVendor(vendors.get(2)),
				Constants.validateEmptyMessage);
		assertEquals(VendorService.validateVendor(vendors.get(3)),
				Constants.validateEmptyMessage);
		assertEquals(VendorService.validateVendor(vendors.get(4)),
				Constants.SUCCESS);
	}

}

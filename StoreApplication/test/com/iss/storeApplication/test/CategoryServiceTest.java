package com.iss.storeApplication.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.iss.storeApplication.business.CategoryService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.domain.Category;

public class CategoryServiceTest {
	@Test
	public void testValidateCategory(){
		
		Category c1 = new Category();
		Category c2 = new Category();
		Category c3 = new Category();
		c1.setCategoryCode("AAACode");
		c2.setCategoryCode("BBBCode");
		c3.setCategoryCode("CCCCode");
		c1.setCategoryName("AAA");
		c2.setCategoryName("BBB");
		c3.setCategoryName("CCC");
		

		assertEquals(CategoryService.validateAndSaveCategory(c1),
				Constants.SUCCESS);
		assertEquals(CategoryService.validateAndSaveCategory(c2),
				Constants.SUCCESS);
		assertEquals(CategoryService.validateAndSaveCategory(c3),
				Constants.SUCCESS);
	}
	
}

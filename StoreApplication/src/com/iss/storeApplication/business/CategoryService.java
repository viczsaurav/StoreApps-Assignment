package com.iss.storeApplication.business;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.dao.CategoryDao;
import com.iss.storeApplication.domain.Category;

/**
 * 
 * @author Luke
 * 
 */

public class CategoryService {

	static CategoryDao categoryDao = new CategoryDao();
	private static String fileName = Constants.FILENAME_CATEGORY
			+ Constants.FILE_EXT_SEPERATOR + Constants.FILE_EXTENSION;

	/**
	 * It validates category against null and duplicates.
	 * 
	 * @param c
	 * @return
	 */
	public static String validateAndSaveCategory(Category c) {
		if (c.getCategoryCode().equals("") && c.getCategoryName().equals("")) {
			// check whether category name and code both are empty?
			return Constants.CATEGORY_CODE_NAME_EMPTY;
		} else if (c.getCategoryName().equals("")) {
			// check whether category name is empty?
			return Constants.CATEGORY_NAME_EMPTY;
		} else if (c.getCategoryCode().equals("")) {
			// check whether category code is empty?
			return Constants.CATEGORY_CODE_EMPTY;
		} else {
			// check whether category is exist?
			Map<String, Category> categories = categoryDao.getMap();
			if (categories.containsKey(c.getCategoryCode())) {
				return Constants.CATEGORY_EXIST;
			}
		}
		addCategory(c);
		return Constants.SUCCESS;
	}



	public static void addCategory(Category c) {
		categoryDao.save(c, true);
	}

	public static boolean saveAll(List<Category> listCategorys) {
		// TODO Auto-generated method stub

		File file = new File(Constants.DATA_FILE_DIR, fileName);

		return categoryDao.save(listCategorys);
	}

}

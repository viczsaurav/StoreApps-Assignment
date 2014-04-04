package com.iss.storeApplication.business;

import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
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
			return Constants.categoryNameCodeEmpty;
		} else if (c.getCategoryName().equals("")) {
			// check whether category name is empty?
			return Constants.categoryNameEmpty;
		} else if (c.getCategoryCode().equals("")) {
			// check whether category code is empty?
			return Constants.categoryCodeEmpty;
		} else {
			// check whether category is exist?
			Map<String, Category> categories = categoryDao.getMap();
			if (categories.containsKey(c.getCategoryCode())) {
				return Constants.categoryExists;
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

		//File file = new File(Constants.DATA_FILE_DIR, fileName);

		return categoryDao.save(listCategorys);
	}
	
	/**
	 * Milan
	 * @return
	 */
	public static List<Category> getAllCategory()
	{
		return categoryDao.retrieveAll();
	}
	
	public static Category getCategory(String categoryCode)
	{
		return categoryDao.get(categoryCode);
	}

}

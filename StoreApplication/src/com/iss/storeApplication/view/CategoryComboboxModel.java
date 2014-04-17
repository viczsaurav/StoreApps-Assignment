package com.iss.storeApplication.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import com.iss.storeApplication.domain.Category;

/**
 * 
 * @author milan
 *
 */
public class CategoryComboboxModel extends AbstractListModel implements
		ComboBoxModel {

	private List<Category> categories;

	JComboBox<Category> categoryCombobox;

	private Map<String, Category> categoryCodeCategoryMap = new HashMap<String, Category>();

	public Map<String, Category> getCategoryCodeCategoryMap() {
		return categoryCodeCategoryMap;
	}

	public void setCategoryCodeCategoryMap(
			Map<String, Category> categoryCodeCategoryMap) {
		this.categoryCodeCategoryMap = categoryCodeCategoryMap;
	}

	private Category selectedCategoy;

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		categoryCodeCategoryMap.clear();
		for (Category c : categories) {
			categoryCodeCategoryMap.put(c.getCategoryCode(), c);
		}
		this.categories = categories;
	}

	public CategoryComboboxModel(JComboBox<Category> categoryCombobox) {
		this.categoryCombobox = categoryCombobox;
		categories = new ArrayList<Category>();
	}

	String selection = null;

	public Object getElementAt(int index) {
		return categories.get(index).getCategoryCode();
	}

	public int getSize() {
		return categories.size();
	}

	public void setSelectedItem(Object anItem) {

		selection = (String) anItem; // to select and register an
	} // item from the pull-down list

	// Methods implemented from the interface ComboBoxModel
	public Category getSelectedCategory() {
		return categoryCodeCategoryMap.get(selection); // to add the selection
														// to the combo box
	}

	public String getSelectedItem() {

		return selection; // to add the selection to the combo box
	}

}

package com.iss.storeApplication.controller;

import java.util.List;

import com.iss.storeApplication.business.CategoryService;
import com.iss.storeApplication.business.DiscountService;
import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.business.MemberRegistrationService;
import com.iss.storeApplication.business.ProductService;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.StoreKeeper;

public class Controller {

	public static String registerMember(MemberCustomer memberCustomer) {
		return MemberRegistrationService.registerMember(memberCustomer);
	}

	/**
	 * 
	 * @author sakthi
	 */
	public static String validateUser(StoreKeeper storeKeeper) {
		return LoginService.validateUser(storeKeeper);
	}

	public static String validateAndSaveDiscount(Discount discount) {
		return DiscountService.validateAndSaveDiscount(discount);

	}
	/**
	 * Discount Controllers
	 * @return
	 */
	public static List<Discount> getDiscounts() {
		return DiscountService.getDiscounts();
	}

	public static boolean isDiscountCodeAlreadyExist(String discountCode) {
		return DiscountService.isDiscountCodeAlreadyExist(discountCode);
	}

	public static boolean saveAll(List<Discount> listDiscounts) {

		return DiscountService.saveAll(listDiscounts);
	}

	public static String validateDiscount(Discount d) {
		return DiscountService.validateDiscount(d);
	}

	/**
	 * Category Controllers
	 * @return
	 */
	public static boolean saveAllCategory(List<Category> listCategorys) {

		return CategoryService.saveAll(listCategorys);
	}

	public static String validateAndSaveCategory(Category c) {
		return CategoryService.validateAndSaveCategory(c);
	}

	/**
	 * Product Controllers
	 * @return
	 */
	public static boolean saveAllProducts(List<Product> listProducts) {

		return ProductService.saveAll(listProducts);
	}
	
	public static Product getProduct(Long barcode) {
		return ProductService.getProduct(barcode);
	}
	
	public static Discount getMaxPublicDiscount()
	{
		return DiscountService.getMaxPublicDiscount();
	}
	
}
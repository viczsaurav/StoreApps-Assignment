package com.iss.storeApplication.controller;

import java.util.List;

import com.iss.storeApplication.business.CategoryService;
import com.iss.storeApplication.business.DiscountService;
import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.business.MemberRegistrationService;
import com.iss.storeApplication.business.ProductService;
import com.iss.storeApplication.business.TransactionService;
import com.iss.storeApplication.business.VendorService;
import com.iss.storeApplication.domain.Category;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.Discount;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.Product;
import com.iss.storeApplication.domain.StoreKeeper;
import com.iss.storeApplication.domain.Transaction;
import com.iss.storeApplication.domain.Vendor;

/**
 * 
 * @author Saurav
 * @author milan
 * @author siva
 * @author sakthi
 */

public class Controller {

	/**
	 * Login Controllers
	 * 
	 * @return
	 */
	
	public static String validateUser(StoreKeeper storeKeeper) {
		return LoginService.validateUser(storeKeeper);
	}

	public static String validateAndSaveDiscount(Discount discount) {
		return DiscountService.validateAndSaveDiscount(discount);

	}

	/**
	 * Discount Controllers
	 * 
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


	public static Discount getMaxPublicDiscount() {
		return DiscountService.getMaxPublicDiscount();
	}

	public static Discount getMaxMemberDiscount() {
		return DiscountService.getMaxMemberDiscount();
	}

	public static Discount getMemberFirstDiscount() {
		return DiscountService.getMemberFirstDiscount();
	}
	
	/**
	 * Category Controllers
	 * 
	 * @return
	 */
	public static boolean saveAllCategory(List<Category> listCategorys) {

		return CategoryService.saveAll(listCategorys);
	}

	public static String validateAndSaveCategory(Category c) {
		return CategoryService.validateAndSaveCategory(c);
	}
	
	public static List<Category> getCategories() {
		return CategoryService.getCategories();
	}

	/**
	 * Product Controllers
	 * 
	 * @return
	 */
	public static boolean saveAllProducts(List<Product> listProducts) {

		return ProductService.saveAll(listProducts);
	}

	public static Product getProduct(Long barcode) {
		return ProductService.getProduct(barcode);
	}
	
	public static String validateAndSaveProduct(Product p) {
		return ProductService.validateAndSaveProduct(p);
	}
	
	public static String validateProduct(Product p) {
		return ProductService.validateProduct(p);
	}
	

	public static List<Product> getProducts() {
		return ProductService.getProducts();
	}


	/**
	 * Customer Controllers
	 * 
	 * @return
	 */
	
	public static String registerMember(MemberCustomer memberCustomer) {
		return MemberRegistrationService.registerMember(memberCustomer);
	}

	
	public static Customer getCustomer(String memberId) {
		return MemberRegistrationService.getCustomer(memberId);
	}
	
	public static boolean editCustomer(Customer member)
	{
		return MemberRegistrationService.editCustomer(member);
	}
	
	public static Integer getMaxTransactionId()
	{
		return TransactionService.getMaxTransactionId();
	}
	
	/**
	 * Transaction Controllers
	 * 
	 * @return
	 */
	public static boolean save(Transaction transaction)
	{
		return TransactionService.save(transaction);
	}
	
	public static List<Category> getAllCategory()
	{
		return CategoryService.getAllCategory();
	}
	
	public static List<Product> getProductsBelowThreshold(Category c){
		return ProductService.getProductsBelowThreshold(c);
	}
	
	public static Vendor getFirstVendor(String categoryCode)
	{
		return VendorService.getFirstVendor(categoryCode);
	}

	public static boolean editProduct(Product p) {
		return ProductService.editProduct(p);
		
	}

	public static List<Transaction> getTransactions()
	{
		return TransactionService.getTransactions();
	}
//Member conrollers
	public static List<Customer> getMemberCustomers() {
		return MemberRegistrationService.getMemberCustomers();
		
	}
	
}
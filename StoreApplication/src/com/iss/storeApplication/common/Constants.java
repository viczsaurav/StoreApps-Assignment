package com.iss.storeApplication.common;

public interface Constants {

	public static final String PRODUCT_NAME = "ProductName";
	public static final String SUCCESS = "SUCCESS";
	public static final String ALWAYS = "Always";
	public static final Object COMMA_DELIMITER = ",";
	public static final String FILE_SEPERATOR = "/";
	public static final String PROPERTY_FILE_NAME = "application_en.properties";
	public static final String DATA_FILE_DIR = "data";
	public static final String PROPERTY_FILE_DIR = "resources";
	public static final String PRODUCT_FILE_NAME = "Products";
	public static final String FILE_EXTENSION = "dat";
	public static final String FILE_EXT_SEPERATOR = ".";
	public static final String CATEGORY_NAME = "CategoryName";
	public static final String ALL_FIELDS_REQUIRED = "All fields are mandatory !";

	/**
	 * 
	 * @author sakthi
	 * 
	 */
	// Login Pop-up Labels and Messages
	public static final String LOGIN_DIALOG_TITLE = "Store Aplication Login";
	public static final String[] LOGINPOPUP_BUTTONTEXT = { "Login", "Cancel" };
	public static final String LOGIN_SUCCESS_MESSAGE = "Login successful!";
	public static final String LOGIN_LABEL_TEXT_USERNAME = "User Name:";
	public static final String LOGIN_LABEL_TEXT_PASSWORD = "Password:";
	public static final String MSG_USR_PWD_NULL = "Please enter Username and Password";
	public static final String MSG_USR_NULL = "Please enter Username";
	public static final String MSG_PWD_NULL = "Please enter Password";
	public static final String MSG_INVALID_USR = "Invalid Username ! Try again";
	public static final String LOGIN_CORRECT_USERNAME = "Please enter correct Username";
	public static final String LOGIN_INVALID_PASSWORD = "Invalid Password";

	// Toolbar Button Labels
	public static final String TRANSACTIONBUTTONLBL = "Transaction";
	public static final String CATEGORYBUTTONLBL = "Category";
	public static final String MEMBERBUTTONLBL = "Members";
	public static final String DISCOUNTBUTTONLBL = "Discount";
	public static final String PURCHASEORDERBUTTONLBL = "Purchase Order";
	public static final String PRODUCTBUTTONLBL = "Products";
	public static final String REPORTBUTTONLBL = "Reports";
	public static final String LOGOUTBUTTONLBL = "Logout";
	public static final String LOGIN_BTN = "login";
	public static final String CANCEL = "cancel";

	public static final String CATEGORY_CLASSNAME = "Category";
	public static final String PRODUCT_CLASSNAME = "Product";
	public static final String STOREKEEPER_CLASSNAME = "StoreKeeper";
	public static final String CUSTOMER_CLASSNAME = "Customer";
	public static final String DISCOUNT_CLASSNAME = "Discount";
	public static final String TRANSACTION_CLASSNAME = "Transaction";

	// Category Button Labels
	public static final String CATEGORYID_LABEL = "Category ID";
	public static final String CATEGORYNAME_LABEL = "Category Name";
	public static final String ADDCATEGORY_BTN = "+ Add New Category";
	public static final String CATEGORY_CODE_NAME_EMPTY = "Category Name and Code are empty";
	public static final String CATEGORY_NAME_EMPTY = "Category Name is empty";
	public static final String CATEGORY_CODE_EMPTY = "Category code is empty";
	public static final String CATEGORY_EXIST = "Category exists";
	public static final String CATEGORY_CORRECT = "Correct Category";
	
	// Product View Labels
	public static final String PRODUCT_CATEGORY_LABEL = "Product Category";
	public static final String PRODUCTID_LABEL = "Product ID";
	public static final String PRODUCTNAME_LABEL = "Product Name";
	public static final String PRODUCT_DESC_LABEL = "Product Description";
	public static final String PRODUCT_QUANTITY_LABEL = "Product Quantity";
	public static final String PRODUCT_PRICE_LABEL = "Product Price";
	public static final String PRODUCT_BARCODE_LABEL = "Product Barcode";
	public static final String PRODUCT_REORDER_QUANT_LABEL = "Product Reorder Threshold";
	public static final String PRODUCT_ORDER_QUANT_LABEL = "Product Order Quantity";
	public static final String ADDPRODUCT_BTN = "+ Add New Product";
	public static final String PRODUCT_CODE_NAME_EMPTY = "Product Name and Code are empty";
	public static final String PRODUCT_NAME_EMPTY = "Product Name is empty";
	public static final String PRODUCT_CODE_EMPTY = "Product code is empty";
	public static final String PRODUCT_EXIST = "Product exists";
	public static final String PRODUCT_CORRECT = "Correct Product";

	// File Names
	public static final String FILENAME_STOREKEEPER = "StoreKeepers";
	public static final String FILENAME_PRODUCT = "Products";
	public static final String FILENAME_CATEGORY = "Category";
	public static final String FILENAME_DISCOUNT = "Discounts";

	// Discount
	public static final String ADDDISCOUNT_BTN = "AddDiscountBtn";
	public static final String DISCOUNT_CODE = "DiscountCode";
	public static final String Description = "Description";
	public static final String discount = "discount";
	public static final String memberApplicable = "memberApplicable";
	public static final String startDate = "startDate";
	public static final String duration = "duration";
	public static final String discountType = "discountType";
	public static final String discountApplicable = "discountApplicable";
	public static final String seasonalDiscount="seasonalDiscount";
	public static final String permanentDiscount="permanentDiscount";
	public static final String validateMessage = "validateMessage";
	public static final String failure = "failure";
	
	
}

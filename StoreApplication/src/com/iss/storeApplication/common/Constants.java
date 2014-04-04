package com.iss.storeApplication.common;

public interface Constants {

	public static final String ProductName = "ProductName";
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
	public static final int DEFAULT_TEXTFIELD_SIZE = 20;
    
	
	/**
	 * 
	 * @author Sakthi
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
	public static final String billing = "billing";
	public static final String category = "category";
	public static final String members = "members";
	public static final String purchaseOrder = "purchaseOrder";
	public static final String products = "products";
	public static final String reports = "reports";
	public static final String logout = "logout";
	public static final String login = "login";
	public static final String cancel = "cancel";

	// Category Button Labels
	public static final String categoryCode = "categoryCode";
	public static final String categoryName = "categoryName";
	public static final String addCategoryBtn = "addCategoryBtn";
	public static final String categoryNameCodeEmpty = "Category Name and Code are empty";
	public static final String categoryNameEmpty = "Category Name is empty";
	public static final String categoryCodeEmpty = "Category Code is empty";
	public static final String categoryExists = "Category already exists";
	public static final String categoryCorrect = "categoryCorrect";
	public static final String categoryAddCategory = "Add Category";
	public static final String categoryDeleteCategory = "Delete Category";
	public static final String categoryEditCategory = "Edit Category";
	public static final String categoryNoLineSelected = "please select one row!";
	public static final String categoryHintForEmptyInDelete = "There is no row to delete!";
	public static final String categoryHintForEmptyInEdit = "There is no row to edit!";
	// Product View Labels
	public static final String productCategory = "productCategory";
	public static final String productId = "productId";
	public static final String productName = "productName";
	public static final String productDescription = "productDescription";
	public static final String productQty = "productQty";
	public static final String productPrice = "productPrice";
	public static final String barcode = "barcode";
	public static final String productReorderThreshold = "productReorderThreshold";
	public static final String productOrderQty = "productOrderQty";
	public static final String addProductBtn = "addProductBtn";
	public static final String editProductBtn = "editProductBtn";
	public static final String deleteProductBtn = "deleteProductBtn";
	public static final String productNameCodeEmpty = "productNameCodeEmpty";
	public static final String productNameEmpty = "productNameEmpty";
	public static final String productCodeEmpty = "productCodeEmpty";
	public static final String productExists = "productExists";
	public static final String productCorrect = "productCorrect";
	public static final String prodReorderQuantDef = "prodReorderQuantDef";
	public static final String prodOrderQuantDef = "prodOrderQuantDef";
	public static final String barcodeExists = "barcodeExists";
	public static final String editProduct = "editProduct";
	public static final String pricemorethanzero = "pricemorethanzero";
	

	// File Names
	public static final String FILENAME_STOREKEEPER = "StoreKeepers";
	public static final String FILENAME_PRODUCT = "Products";
	public static final String FILENAME_CATEGORY = "Category";
	public static final String FILENAME_DISCOUNT = "Discounts";
	public static final String FILENAME_MEMBER = "Members";
	
	// Discount
	public static final String addDiscountBtn = "addDiscountBtn";
	public static final String discountCode = "discountCode";
	public static final String Description = "Description";
	public static final String discount = "discount";
	public static final String memberApplicable = "memberApplicable";
	public static final String startDate = "startDate";
	public static final String duration = "duration";
	public static final String discountType = "discountType";
	public static final String discountApplicable = "discountApplicable";
	public static final String seasonalDiscount="seasonalDiscount";
	public static final String permanentDiscount="permanentDiscount";
	public static final String validateEmptyMessage = "validateEmptyMessage";
	public static final String failure = "failure";
	public static final String dcAlreadyExist = "dcAlreadyExist";
	public static final String editDiscount = "editDiscount";
	public static final String selectRow = "selectRow";
	public static final String msgnotGrtThan100 = "msgnotGrtThan100";
	public static final String msgnotNegative = "msgnotNegative";
	public static final String find = "find";
	public static final String barcodeNotNull = "barcodeNotNull";
	public static final String productNotFound = "productNotFound";
	public static final String purchaseQty = "purchaseQty";
	public static final String purchasedQtyNotNegative = "purchasedQtyNotNegative";
	public static final String remove = "remove";
	public static final String msgChgQty = "msgChgQty";
	public static final String total = "total";
	public static final String memberType = "memberType";
	public static final String memberId = "memberId";
	public static final String applyDiscount = "applyDiscount";
	public static final String billAmt = "billAmt";
	public static final String generateBill = "generateBill";
	public static final String reset = "reset";
	public static final String loyalityEarned = "loyalityEarned";
	public static final String reedemPoint = "reedemPoint";
	
	//Report Tab	
	
	public static final String CATEGORY_REPORT_BUTTON = "categoryReportButton";
	public static final String PRODUCT_REPORT_BUTTON = "productReportButton";
	public static final String TRANSACTION_REPORT_BUTTON = "transactionReportButton";
	public static final String MEMBER_REPORT_BUTTON = "memeberReportButton";
	public static final String GENERATE_TRANSACTIONS_BUTTON = "generateTransactions";
	public static final String MEMBER_FIRST = "MEMBER_FIRST";
	public static final String msgEnterMemId = "msgEnterMemId";
	public static final String msgMemNotFound = "msgMemNotFound";
	public static final String msgCannotReedemMoreThanEarned = "msgCannotReedemMoreThanEarned";
	public static final String FILENAME_TRANSACTION = "Transactions";
	public static final String PUBLIC = "PUBLIC";
	public static final String transactionId = "transactionId";
	public static final String puchaseDate = "puchaseDate";
	public static final String billTitle = "billTitle";
	public static final String generatePurchaseOrder = "generatePurchaseOrder";
	public static final String FILENAME_VENDORS = "Vendors";
	public static final String msgNoVendorFound = "msgNoVendorFound";
	public static final String vendorName = "vendorName";
	public static final String purchaseOrderDate = "purchaseOrderDate";
	public static final String endDate = "endDate";
	public static final String dateIntervalInvalid = "dateIntervalInvalid";
	public static final String selectDateFromBelow = "selectDateFromBelow";
	
//Member
	public static final String memberName = "memberName";
	public static final String loyality = "loyality";
	public static final String addMemberBtn = "addMemberBtn";
	public static final String editMemberBtn = "editMemberBtn";
	public static final String deleteMember = "deleteMember";
	public static final String defaultLoyality= "defaultLoyality";
	public static final String editMember= "editMember";
	public static final String addVendor = "addVendor";
	public static final String vendorDesc = "vendorDesc";
	public static final String msgVendorAddedSuccess = "msgVendorAddedSuccess";
	public static final String vendor = "vendor";
	public static final String deleteVendor = "deleteVendor";
	public static final String msgVendorAlreadyExist = "msgVendorAlreadyExist";
	public static final String memberExists = "Member already exists";
	public static final String purchasedQtyNotGreaterThanAvailable = "purchasedQtyNotGreaterThanAvailable";
	public static final String msgdiscountNumeric = "msgdiscountNumeric";
	public static final String msgdurationNumeric = "msgdurationNumeric";
}
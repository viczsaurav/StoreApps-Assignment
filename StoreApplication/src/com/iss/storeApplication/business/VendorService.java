package com.iss.storeApplication.business;

// test
import java.util.List;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.StringUtility;
import com.iss.storeApplication.dao.VendorDao;
import com.iss.storeApplication.domain.Vendor;

/*
 * Milan
 */
public class VendorService {

	private static VendorDao vendorDao = new VendorDao();

	public static Vendor getFirstVendor(String categoryCode) {
		return vendorDao.getFirstVendor(categoryCode);
	}

	public static String validateVendor(Vendor v) {
		if (StringUtility.isEmpty(v.getName())
				| StringUtility.isEmpty(v.getDescription())
				| v.getCategories().size() == 0) {
			return Constants.validateEmptyMessage;
		}
		
		if(isVendorExist(v))
		{
			return Constants.msgVendorAlreadyExist;
		}
		
		return Constants.SUCCESS;
	}
	
	public static boolean isVendorExist(Vendor v)
	{
		return retrieveAllVendor().contains(v);
	}

	public static boolean saveVendor(Vendor v) {
		return vendorDao.save(v, true);
	}

	public static List<Vendor> retrieveAllVendor() {
		return vendorDao.retrieveAll();
	}

	public static boolean deleteVendor(Vendor vendor) {
		
		return vendorDao.delete(vendor);
	}
}

package com.iss.storeApplication.business;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.dao.CustomerDao;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.MemberCustomer;

public class MemberRegistrationService {

	private static CustomerDao memberDao = new CustomerDao();

	public static String registerMember(MemberCustomer memberCustomer) {

		// validation Test driven
		// call dataAccess for storing
		// return message to display on screen
		return Constants.SUCCESS;
	}

	public static Customer getCustomer(String memberId) {
		return memberDao.getCustomer(memberId);
	}
	
	public static boolean editCustomer(Customer member)
	{
		return memberDao.edit(member);
	}

}

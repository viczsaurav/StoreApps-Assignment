package com.iss.storeApplication.business;

import java.util.List;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.dao.CustomerDao;
import com.iss.storeApplication.domain.Customer;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.Product;

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

	public static List<Customer> getMemberCustomers() {
		return memberDao.retrieveAll();
		
	}

	public static String validateAndSaveMember(MemberCustomer newMember) {
		String msg = validateMember(newMember);
		if (msg.equals(Constants.SUCCESS)) {
			
			if (memberDao.save(newMember, true))
				return Constants.SUCCESS;
			else
				return Utility.getPropertyValue(Constants.failure);
		} else {
			return msg;
		}
	}

	private static String validateMember(MemberCustomer newMember) {
		if (newMember == null) {
			return Utility.getPropertyValue(Constants.validateEmptyMessage);
		}
		if (	newMember.getMemberName() == null 	|| 
				newMember.getMemberId() == null	|| 
				newMember.getLoyality() == null 	) 	{
			return Constants.ALL_FIELDS_REQUIRED;
		}

		return Constants.SUCCESS;
	}

	

}

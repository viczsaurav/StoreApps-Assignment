package com.iss.storeApplication.business;

import java.util.List;
import java.util.Map;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.dao.CustomerDao;
import com.iss.storeApplication.domain.Category;
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
		Map<String, Customer> members= memberDao.getMap();
		if (msg.equals(Constants.SUCCESS)) {
			
			
			 if (members.containsKey(newMember.getMemberId()))
				return Constants.memberExists;
			 else if (memberDao.save(newMember, true))
					return Constants.SUCCESS;
			 else
				return Utility.getPropertyValue(Constants.failure);
		} else {
			return msg;
		}
	}

	public static String validateMember(MemberCustomer newMember) {
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

	public static boolean  memberExists(MemberCustomer newMember){
		Map<String, Customer> members= memberDao.getMap();
		if (members.containsKey(newMember.getMemberId()))
			return false;
		else 
			return true;
	}
	
	
	
	public static boolean saveAll(List<Customer> members) {
		// TODO Auto-generated method stub
		return memberDao.saveAll(members);
	}

	
	

	

}

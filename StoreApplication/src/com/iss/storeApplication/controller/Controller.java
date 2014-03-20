package com.iss.storeApplication.controller;

import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.business.MemberRegistrationService;
import com.iss.storeApplication.domain.MemberCustomer;
import com.iss.storeApplication.domain.StoreKeeper;

public  class Controller {
	
	
	
	public static String registerMember(MemberCustomer memberCustomer)
	{
		return MemberRegistrationService.registerMember(memberCustomer);
	}
	/**
	 * 
	 * @author sakthi
	 *
	 */
	public static boolean validateUser(StoreKeeper storeKeeper)
	{
		return LoginService.validateUser(storeKeeper);
	}
}

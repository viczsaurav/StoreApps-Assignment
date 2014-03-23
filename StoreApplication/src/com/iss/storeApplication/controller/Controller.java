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
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 *
	 */
	public static String validateUser(StoreKeeper storeKeeper) throws InstantiationException, IllegalAccessException
	{
		return LoginService.validateUser(storeKeeper);
	}
}

package com.iss.storeApplication.controller;

import com.iss.storeApplication.business.MemberRegistrationService;
import com.iss.storeApplication.domain.MemberCustomer;

public  class Controller {
	
	
	
	public static String registerMember(MemberCustomer memberCustomer)
	{
		return MemberRegistrationService.registerMember(memberCustomer);
	}
}

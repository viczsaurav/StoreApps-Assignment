package com.iss.storeApplication.business;

import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.dao.MemberDao;
import com.iss.storeApplication.domain.Member;
import com.iss.storeApplication.domain.MemberCustomer;

public class MemberRegistrationService {

	private static MemberDao memberDao = new MemberDao();

	public static String registerMember(MemberCustomer memberCustomer) {

		// validation Test driven
		// call dataAccess for storing
		// return message to display on screen
		return Constants.SUCCESS;
	}

	public static Member getMember(String memberId) {
		return memberDao.getMember(memberId);
	}
	
	public static boolean editMember(Member member)
	{
		return memberDao.edit(member);
	}

}

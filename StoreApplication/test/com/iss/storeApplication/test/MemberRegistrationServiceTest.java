package com.iss.storeApplication.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.iss.storeApplication.business.MemberRegistrationService;
import com.iss.storeApplication.business.VendorService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.common.Utility;
import com.iss.storeApplication.domain.MemberCustomer;

public class MemberRegistrationServiceTest {

	private static List<MemberCustomer> testMembers = new ArrayList<MemberCustomer>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
	
   //+ve test case -membercustomer with non null attributes
	MemberCustomer m=new MemberCustomer();
	m.setMemberName("IJK");
	m.setMemberId("M4");
	m.setLoyality(50);
	testMembers.add(m);
	
	
	//-ve test case -null membercustomer object
	m=null;
	testMembers.add(m);
	
	
	
	//-ve test case - membercustomer object with null in loyality
	m=new MemberCustomer();
	m.setMemberName("DEF");
	m.setMemberId("M2");
	m.setLoyality(null);
	testMembers.add(m);
	
	//-ve test case - membercustomer object with null in memberId
	m=new MemberCustomer();
	m.setMemberName("GHI");
	m.setMemberId(null);
	m.setLoyality(40);
	testMembers.add(m);	
	//-ve test case - membercustomer object with null in memberName
	m=new MemberCustomer();
	m.setMemberName(null);
	m.setMemberId("M3");
	m.setLoyality(20);
	testMembers.add(m);			
	//-ve test case - existing member
	m=new MemberCustomer();
	m.setMemberName("ABC");
	m.setMemberId("M1");
	m.setLoyality(30);
	testMembers.add(m);	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		testMembers.clear();
	}
	@Test
	public void test() {
		assertEquals(MemberRegistrationService.validateMember(testMembers.get(0)),Constants.SUCCESS);
		assertEquals(MemberRegistrationService.validateMember(testMembers.get(1)),Utility.getPropertyValue(Constants.validateEmptyMessage));
		assertEquals(MemberRegistrationService.validateMember(testMembers.get(2)),Constants.ALL_FIELDS_REQUIRED);
		assertEquals(MemberRegistrationService.validateMember(testMembers.get(3)),Constants.ALL_FIELDS_REQUIRED);
		assertEquals(MemberRegistrationService.validateMember(testMembers.get(4)),Constants.ALL_FIELDS_REQUIRED);
		assertEquals(MemberRegistrationService.validateMember(testMembers.get(5)),Constants.memberExists);
	}
}

package com.iss.storeApplication.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.common.Constansts;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */

public class LoginServiceTest {
	private List<StoreKeeper> storeKeepers=new ArrayList<StoreKeeper>();

	@Test
	public void test() {
		storeKeepers.add(new StoreKeeper("",""));
		storeKeepers.add(new StoreKeeper("wrongUserName",""));
		storeKeepers.add(new StoreKeeper("","wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName","wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("correctUserName","wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName","correctPasswrod"));
		storeKeepers.add(new StoreKeeper("correctUserName","correctPasswrod"));
		storeKeepers.add(new StoreKeeper("Three","Three"));
		
		assertEquals(LoginService.validateUser(storeKeepers.get(0)),Constansts.MSG_USR_PWD_NULL);		
		//assertFalse(LoginService.validateUser(storeKeepers.get(1)));
		//assertFalse(LoginService.validateUser(storeKeepers.get(2)));
		//assertFalse(LoginService.validateUser(storeKeepers.get(3)));
		//assertFalse(LoginService.validateUser(storeKeepers.get(4)));
		//assertFalse(LoginService.validateUser(storeKeepers.get(5)));
		//assertTrue(LoginService.validateUser(storeKeepers.get(6)));
	}

}

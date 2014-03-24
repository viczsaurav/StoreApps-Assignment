package com.iss.storeApplication.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.iss.storeApplication.business.LoginService;
import com.iss.storeApplication.common.Constants;
import com.iss.storeApplication.domain.StoreKeeper;

/**
 * 
 * @author sakthi
 * 
 */

public class LoginServiceTest {
	private List<StoreKeeper> storeKeepers = new ArrayList<StoreKeeper>();

	@Test
	public void test() throws InstantiationException, IllegalAccessException {
		storeKeepers.add(new StoreKeeper("", ""));
		storeKeepers.add(new StoreKeeper("wrongUserName", ""));
		storeKeepers.add(new StoreKeeper("", "wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName", "wrongPasswrod"));
		/*storeKeepers.add(new StoreKeeper("Three", "wrongPasswrod"));
		storeKeepers.add(new StoreKeeper("wrongUserName", "Three"));*/
		 storeKeepers.add(new
		 StoreKeeper("sakthi","sakthi"));
		storeKeepers.add(new StoreKeeper("storekeeper", "storekeeper"));

		assertEquals(LoginService.validateUser(storeKeepers.get(0)),
				Constants.MSG_USR_PWD_NULL);
		assertEquals(LoginService.validateUser(storeKeepers.get(1)),
				Constants.MSG_PWD_NULL);
		assertEquals(LoginService.validateUser(storeKeepers.get(2)),
				Constants.MSG_USR_NULL);
		assertEquals(LoginService.validateUser(storeKeepers.get(3)),
				Constants.MSG_INVALID_USR);
		/*assertEquals(LoginService.validateUser(storeKeepers.get(4)),
				Constants.LOGIN_INVALID_PASSWORD);
		assertEquals(LoginService.validateUser(storeKeepers.get(5)),
				Constants.LOGIN_CORRECT_USERNAME);*/
		assertEquals(LoginService.validateUser(storeKeepers.get(4)),
				Constants.LOGIN_SUCCESS_MESSAGE);

	}

}
